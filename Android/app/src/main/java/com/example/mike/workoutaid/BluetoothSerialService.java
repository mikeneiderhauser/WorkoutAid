package com.example.mike.workoutaid;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class BluetoothSerialService extends Service
{
    //intent UUID connection string
    private static final UUID SerialPortServiceClass_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //Tag for logging
    private static final String TAG = "BluetoothSerialService";
    //allow debug flag
    private static final boolean D = true;
    //RX from remote device buffer size
    private static final int THREAD_BUFFER_SIZE = 128;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Broadcast RX constants and parameters
    public static final String BLUETOOTH_SERVICE_COMMAND = "com.example.mike.workoutaid.bluetooth.bluetootService.command";
    public static final String BLUETOOTH_SERVICE_TOSEND = "com.example.mike.workoutaid.bluetooth.bluetoothService.tosend";

    //Broadcast RX commands to control service
    public static final int COMMAND_START = 0;
    public static final int COMMAND_STOP = 1;
    public static final int COMMAND_CONNECT = 2;
    public static final int COMMAND_GET_DEV_ID = 3;
    public static final int COMMAND_GET_STATE = 4;

    //Broadcast RX intent fields
    public static final String COMMAND_PARAM_OP = "COMMAND";
    public static final String COMMAND_PARAM_MAC = "DEVICE_ID";
    public static final String COMMAND_PARAM_STATE = "BLUETOOTH_SERIAL_SERVICE_STATE";

    public static final String BLUETOOTH_SERVICE_TOSEND_DATA = "TOSEND_BUFFER";

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //BROADCAST TX identifiers
    public static final String BLUETOOTH_SERVICE_COMMAND_RESPONSE = "com.example.mike.workoutaid.bluetooth.bluetootService.command.response";//response to command request
    public static final String BLUETOOTH_SERVICE_ALERT = "com.example.mike.workoutaid.bluetooth.bluetoothService.alert";//service alerts
    public static final String BLUETOOTH_SERVICE_MESSAGE = "com.example.mike.workoutaid.bluetooth.bluetoothService.message";//from bluetooth device

    //BROADCAST TX parameters
    public static final String BLUETOOTH_SERVICE_PARAM_MESSAGE = "BLUETOOTH_DEVICE_MESSAGE";
    public static final String BLUETOOTH_SERVICE_PARAM_MESSAGE_CODE = "BLUETOOTH_DEVICE_MESSAGE_CODE";
    public static final String BLUETOOTH_SERVICE_PARAM_ALERT = "BLUETOOTH_SERVICE_ALERT_MESSAGE";
    public static final String BLUETOOTH_SERVICE_PARAM_ALERT_CODE = "BLUETOOTH_SERVICE_ALERT_CODE";

    //BROADCAST TX Message type code constants
    public static final int BLUETOOTH_SERVICE_MESSAGE_TYPE_RX = 0;
    public static final int BLUETOOTH_SERVICE_MESSAGE_TYPE_TX = 1;

    //BROADCAST TX Alert type code constants
    public static final int ALERT_ID_STATE = 0;
    public static final int ALERT_ID_CONNECTED = 1;
    public static final int ALERT_ID_CONNECTION_FAILED = 2;
    public static final int ALERT_ID_CONNECTION_LOST = 3;
    public static final int ALERT_ID_RESPONSE = 4;
    public static final int ALERT_ID_INVALID_COMMAND = 5;
    public static final int ALERT_ID_OTHER = 6;


    //Global Service Variables
    private BluetoothAdapter mAdapter;
    //private Handler mHandler;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private int mState;

    // Constants that indicate the current connection state
    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 3;  // now connected to a remote device

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        //call super
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        Log.d(TAG,"BT SERVICE STOPED");
        myStop();
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);

        myStart();
        registerReceiver(broadcastReceiver, new IntentFilter(BluetoothSerialService.BLUETOOTH_SERVICE_COMMAND));
        registerReceiver(broadcastReceiver,new IntentFilter(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND));
        Log.d(TAG,"BT SERVICE STARTED");

        return START_STICKY;
    }

    /**
     * Set the current state of the chat connection
     * @param state  An integer defining the current connection state
     */
    private synchronized void setState(int state) {
        if (D) Log.d(TAG, "setState() " + mState + " -> " + state);
        mState = state;

        broadcastAlert(""+mState,BluetoothSerialService.ALERT_ID_STATE);

        // Give the new state to the Handler so the UI Activity can update
        //mHandler.obtainMessage(BlueTerm.MESSAGE_STATE_CHANGE, state, -1).sendToTarget();
    }

    /**
     * Return the current connection state. */
    public synchronized int getState() {
        return mState;
    }

    private synchronized void myStart()
    {
        if(D) Log.d(TAG,"myStart()");

        //cancel any thread attempting to make a connection
        if(mConnectThread!=null)
        {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        //cancel any thread currently running a connection
        if(mConnectedThread != null)
        {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        setState(STATE_NONE);

        mAdapter = BluetoothAdapter.getDefaultAdapter();
    }//end myStart

    private synchronized void myStop()
    {
        if (D) Log.d(TAG, "stop");


        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        setState(STATE_NONE);
    }

    public void myWrite(byte[] out) {
        // Create temporary object
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (mState != STATE_CONNECTED) return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    public synchronized void connect(String MAC)
    {
        if(D) Log.d(TAG,"Connect to: "+MAC);

        //cancel possible connecting thread
        if(mState == STATE_CONNECTING)
        {
            if(mConnectThread!=null)
            {
                mConnectThread.cancel();
                mConnectThread = null;

            }
        }//end if state connecting

        if(mConnectedThread!=null)
        {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        if(!BluetoothAdapter.checkBluetoothAddress(MAC))
        {
            if(D)Log.d(TAG,"BLUETOOTH ADDRESS INVALID.. ATTEMPTING FIX");
            //if address is not valid.. try to fix it.. somehow
            MAC = MAC.toUpperCase(Locale.ENGLISH);
        }

        if(BluetoothAdapter.checkBluetoothAddress(MAC))
        {
            if(D)Log.d(TAG,"BLUETOOTH ADDRESS VALID");
            myStart();
            BluetoothDevice dev = mAdapter.getRemoteDevice(MAC);
            mConnectThread = new ConnectThread(dev);
            mConnectThread.start();
            setState(STATE_CONNECTING);
        }
        else
        {
            if(D)Log.d(TAG,"BLUETOOTH ADDRESS INVALID. NO LONGER CONNECTING");
            setState(STATE_NONE);

            broadcastAlert("Connection Failed: Invalid MAC Address", BluetoothSerialService.ALERT_ID_CONNECTION_FAILED);
        }

    }//end connect

    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     * @param socket  The BluetoothSocket on which the connection was made
     * @param device  The BluetoothDevice that has been connected
     */
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {
        if (D) Log.d(TAG, "connected");

        // Cancel the thread that completed the connection
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        broadcastAlert("Device has connected", BluetoothSerialService.ALERT_ID_CONNECTED);

        // Send the name of the connected device back to the UI Activity
        /*Message msg = mHandler.obtainMessage(BlueTerm.MESSAGE_DEVICE_NAME);
        Bundle bundle = new Bundle();
        bundle.putString(BlueTerm.DEVICE_NAME, device.getName());
        msg.setData(bundle);
        mHandler.sendMessage(msg);
		*/

        setState(STATE_CONNECTED);
    }

    //Indicate that the connection attempt failed and notify the UI Activity.
    private void connectionFailed() {
        setState(STATE_NONE);
        broadcastAlert("Unable to connect to device", BluetoothSerialService.ALERT_ID_CONNECTION_FAILED);

        // Send a failure message back to the Activity
        // Message msg = mHandler.obtainMessage(BlueTerm.MESSAGE_TOAST);
        // Bundle bundle = new Bundle();
        // bundle.putString(BlueTerm.TOAST, "Unable to connect device");
        // msg.setData(bundle);
        // mHandler.sendMessage(msg);
    }

    //Indicate that the connection was lost and notify the UI Activity.
    private void connectionLost()
    {
        setState(STATE_NONE);
        broadcastAlert("Device connection was lost",BluetoothSerialService.ALERT_ID_CONNECTION_LOST);

        // Send a failure message back to the Activity
        /*Message msg = mHandler.obtainMessage(BlueTerm.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(BlueTerm.TOAST, "Device connection was lost");
        msg.setData(bundle);
        mHandler.sendMessage(msg);
        */
    }

    private class ConnectThread extends Thread
    {
        private BluetoothSocket mmSocket;
        private BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device)
        {
            //set private class var device
            mmDevice = device;

            //temp holder for bluetooth socket
            BluetoothSocket tmp = null;

            //attempt to grab socket
            try
            {
                tmp = device.createRfcommSocketToServiceRecord(SerialPortServiceClass_UUID);
            }
            catch(IOException e)
            {
                Log.e(TAG,"create() failed",e);
            }
            //socket obtained.  set as mmSocket
            mmSocket = tmp;
        }//end constructor

        public void run()
        {
            Log.i(TAG,"Begin mConnectThread");
            setName("ConnectThread");

            // Always cancel discovery because it will slow down a connection
            mAdapter.cancelDiscovery();

            //make a connection to the socket
            try
            {
                //this is a blocking call and will only return on a
                //Successful connection or an exception
                if(mmSocket != null)
                {
                    mmSocket.connect();
                }
            }
            catch(IOException e)
            {
                connectionFailed();
                //close the socket
                try
                {
                    if(mmSocket!=null)
                    {
                        mmSocket.close();
                    }
                }
                catch(IOException e2)
                {
                    Log.e(TAG,"unable to close() socket during connection failure",e);
                }
                return;
            }

            //Reset the connectThread. Pack up.. we are done here
            synchronized (BluetoothSerialService.this)
            {
                mConnectThread = null;
            }

            //start the connected thread
            connected(mmSocket,mmDevice);
        }//end run

        public void cancel()
        {
            try
            {
                mmSocket.close();
            }
            catch(IOException e)
            {
                Log.e(TAG,"close() of connect socket failed,e");
            }
        }//end cancel
    }

    private class ConnectedThread extends Thread
    {
        private BluetoothSocket mmSocket;
        private InputStream mmInStream;
        private OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket)
        {
            Log.d(TAG,"create ConnectedThread");

            mmSocket=socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try
            {
                tmpIn = mmSocket.getInputStream();
                tmpOut = mmSocket.getOutputStream();
            }
            catch(IOException e)
            {
                Log.e(TAG,"temp sockets not created",e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            Log.i(TAG,"Begin mConnected Thread");
            int buffer_bytes;
            int temp_bytes = 0;
            boolean term_found = false;
            byte[] buffer = new byte[THREAD_BUFFER_SIZE];
            char [] temp = new char[THREAD_BUFFER_SIZE];
            for(int j = 0;  j<THREAD_BUFFER_SIZE; j++)
            {
                buffer[j] = (byte)'\0';
                temp[j] = '\0';
            }

            //keep listening to the Input Stream while connected
            while(true)
            {
                try
                {
                    // Read input stream to get number of bytes
                    buffer_bytes = mmInStream.read(buffer);
                    // copy num bytes into temp buffer
                    for(int i = 0; i < buffer_bytes; i++) {
                        // Terminating Sequence
                        // i = Line Feed -> 0xA
                        // i+1 = Carriage Return -> 0xD
                        // i+2 = NULL -> 0x0
                        if((i+2) < buffer_bytes)
                        {
                            if((buffer[i] == 0xA) && (buffer[i+1] == 0xD) && (buffer[i+2] == 0x0))
                            {
                                // Terminating Sequence Found
                                Log.i(TAG, "Found Terminating Sequence");

                                // Copy to temp buffer
                                temp[i+temp_bytes] = (char)buffer[i];
                                temp[(i+1)+temp_bytes] = (char)buffer[i+1];
                                temp[(i+2)+temp_bytes] = (char)buffer[i+2];

                                // Set termination sequence found
                                term_found = true;
                                break;
                            }

                        }

                        temp[i+temp_bytes] = (char)buffer[i]; // copy buffer into temp
                    }
                    // resize size of tmp_bytes to include new buffer
                    temp_bytes += buffer_bytes;

                    // process broadcast
                    if(term_found) {
                        if(temp_bytes != 0) {
                            // send bluetooth message
                            Log.i(TAG, "Broadcast Temp Buffer");
                            String msg = new String(temp); // create msg
                            broadcastMessage(msg, BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE_TYPE_RX); // send broadcast
                        }

                        // reset
                        term_found = false;
                        buffer_bytes = 0;
                        temp_bytes = 0;
                        for(int i = 0; i < THREAD_BUFFER_SIZE; i++)
                        {
                            buffer[i]='\0';
                            temp[i] = '\0';
                        }
                    }
                }
                catch(IOException e)
                {
                    Log.e(TAG,"disconnected",e);
                    connectionLost();
                    break;//exit while loop
                }
            }//end while true
        }//end run

        //write buffer to socket
        public void write(byte [] buffer)
        {
            try
            {
                mmOutStream.write(buffer);
                char [] temp = new char[buffer.length];
                for(int i =0; i<buffer.length;i++)
                {
                    temp[i] = (char)buffer[i];
                }
                String msg =new String(temp);
                broadcastMessage(msg,BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE_TYPE_TX);
            }
            catch(IOException e)
            {
                Log.e(TAG,"Exception during transmit/write on socket",e);
            }

        }

        public void cancel()
        {
            try
            {
                mmSocket.close();
            }
            catch(IOException e)
            {
                Log.e(TAG,"close() of connected socket failed",e);
            }
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //handle RX messages
            processIntent(intent);
        }
    };

    private void processIntent(Intent intent)
    {
        if(intent.getAction().equals(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND))
        {
            byte [] buf = intent.getByteArrayExtra(BLUETOOTH_SERVICE_TOSEND_DATA);
            if(buf!=null)
            {
                myWrite(buf);
            }
            else
            {
                broadcastAlert("Invalid to send data",BluetoothSerialService.ALERT_ID_INVALID_COMMAND);
            }

        }
        else if(intent.getAction().equals(BluetoothSerialService.BLUETOOTH_SERVICE_COMMAND))
        {
            int command = intent.getIntExtra(BluetoothSerialService.COMMAND_PARAM_OP, -1);

            if(command == BluetoothSerialService.COMMAND_START)
            {
                myStart();
            }
            else if(command == BluetoothSerialService.COMMAND_STOP)
            {
                myStop();
            }
            else if(command == BluetoothSerialService.COMMAND_CONNECT)
            {
                if (D) Log.d(TAG,"CONNECTION REQUEST");
                String myMAC = intent.getStringExtra(BluetoothSerialService.COMMAND_PARAM_MAC);
                if(myMAC!=null)
                {
                    connect(myMAC);
                }
                else
                {
                    broadcastAlert("Connect: MAC Address not populated. Ignoring connect request",BluetoothSerialService.ALERT_ID_RESPONSE);
                }
            }
            else if(command == BluetoothSerialService.COMMAND_GET_DEV_ID)
            {
                //TODO send Broadcast alert with MAC
                broadcastAlert("NOT IMPLEMENTED",BluetoothSerialService.ALERT_ID_RESPONSE);
            }
            else if(command == BluetoothSerialService.COMMAND_GET_STATE)
            {
                if(D)Log.d(TAG,"Sending State");
                broadcastAlert(""+mState,BluetoothSerialService.ALERT_ID_STATE);
            }
            else
            {
                broadcastAlert("Broadcast RX: Invalid Command",BluetoothSerialService.ALERT_ID_INVALID_COMMAND);
            }

        }
    }//end process intent

    private void broadcastAlert(String message, int id)
    {
        Intent i = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_ALERT);
        i.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_ALERT_CODE, id);
        i.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_ALERT, message);
        sendBroadcast(i);
    }

    private void broadcastMessage(String message, int type)
    {
        Intent i = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE);
        i.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_MESSAGE_CODE, type);
        i.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_MESSAGE, message);
        sendBroadcast(i);
    }
}
