package com.example.mike.workoutaid;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class BackgroundService extends Service
{
    private boolean D = true;  // Debugging Flag
    private static final String TAG = "BackgroundService"; // TAG used for log messages
    Context serviceContext;

    /* Commands */
    public static final char CMD_SET_LINE = 'l';
    public static final char CMD_SET_MUTE = 'm';
    public static final char CMD_SET_PLAY = 'p';
    public static final char CMD_SET_TIME = 't';
    public static final char CMD_SET_STATE = 's';
    public static final char CMD_GET_BAT = 'g';
    public static final char CMD_BTN_STATE = 'c';
    public static final char CMD_BAT_UPDATE = 'b';
    public static final char CMD_ERROR = 'e';

    /* States */
    public static final byte MUTE_DISABLED = 0;
    public static final byte MUTE_ENABLED = 1;

    public static final byte PLAYER_PLAYING = 0;
    public static final byte PLAYER_PAUSED = 1;
    public static final byte PLAYER_STOPPED = 2;

    /* Key Events */
    private static String CMD_MEDIA_VOL_UP = "input keyevent 24";
    private static String CMD_MEDIA_VOL_DOWN = "input keyevent 25";
    private static String CMD_MEDIA_VOL_MUTE_TOG = "input keyevent 164";
    private static String CMD_MEDIA_NEXT = "input keyevent 87";
    private static String CMD_MEDIA_PREV = "input keyevent 88";
    private static String CMD_MEDIA_PAUSE_PLAY = "input keyevent 85";

    private Handler connectionHandler = new Handler();  // Handler for BT Connection
    private String _myMac="00:06:66:4C:CB:CB";  // BT MAC Address.  TODO. Make configurable
    private int state = 0;  // BT connection State
    private int prevState = 0; // Prev BT Connection State
    private static int connectionPeriod = 5000;   // Check every connectionPeriod for BT connection

    private byte muteState = MUTE_DISABLED;
    private byte prevMuteState = MUTE_DISABLED;

    private byte prevButtonState = 0; // Previously Rx'd button State

    private boolean timerRequest = false;

    private SpotifyBCR srx; // Spotify Broadcast Receiver

    public static final String APPLYMAC = "com.example.mike.workoutaid.backgroundService.ApplyMac";
    public static final String MAC = "mac";

    AudioManager audio;

    Timer clockTimer;
    Timer muteStatusTimer;

    @Override
    public IBinder onBind(Intent intent)
    {
        if(D)Log.d(TAG,"Binding");
        return null;
    }

    // Service Destroyed. Cleanup
    @Override
    public void onDestroy()
    {
        // Cancel Timer Events
        clockTimer.cancel();
        muteStatusTimer.cancel();

        // Stop BT device polling
        connectionHandler.removeCallbacks(pollStatus);

        // un-subscribe to broadcasts
        unregisterReceiver(broadcastReceiver);
        unregisterReceiver(srx);
        //call super
        super.onDestroy();
    }

    // Service Start command.  Does init and registers receivers.
    @Override
    public int onStartCommand (Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);

        addSysLog("Started Background Service");

        // subscribe to bluetooth messages
        registerReceiver(broadcastReceiver, new IntentFilter(BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE));
        registerReceiver(broadcastReceiver, new IntentFilter(BluetoothSerialService.BLUETOOTH_SERVICE_ALERT));

        // Init Spotify BCR and subscribe to meta and playback state change
        srx = new SpotifyBCR();
        registerReceiver(srx, new IntentFilter("com.spotify.music.playbackstatechanged"));
        registerReceiver(srx, new IntentFilter("com.spotify.music.metadatachanged"));

        // registerReceiver(broadcastReceiver, new IntentFilter(BackgroundService.APPLYMAC)); // TODO

        // Set up connection polling
        connectionHandler.removeCallbacks(pollStatus);
        //launch event after 1 second
        connectionHandler.postDelayed(pollStatus,connectionPeriod);//period

        // Get service context
        serviceContext = getApplicationContext();

        // Init audio manager. Used for obtaining media volume (eg mute)
        audio = (AudioManager) serviceContext.getSystemService(Context.AUDIO_SERVICE);

        // Set up time update Timer
        Calendar c = Calendar.getInstance();
        clockTimer = new Timer();
        // Execute minuteTick TimerTask every 60 seconds. Delayed Start
        clockTimer.schedule(minuteTick, (long)((60-c.get(Calendar.SECOND))*1000), 1000*60);

        // Set up mute status Timer
        muteStatusTimer = new Timer();
        // Execute TimerTask checkMuteStatus every 0 seconds. Immediate start.
        muteStatusTimer.schedule(checkMuteStatus, 0, 1000*5);

        return START_STICKY;
    }

    // Handles all broadcasts the service is listening to
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals(BackgroundService.APPLYMAC))
            {
                String m = intent.getStringExtra(BackgroundService.MAC);
                _myMac = m;
            }
            else if(intent.getAction().equals(BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE))
            {
                String msg = intent.getStringExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_MESSAGE);
                int mode = intent.getIntExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_MESSAGE_CODE,-1);

                if(mode == 0)
                {
                    //data from BT
                    if(msg!=null)
                    {
                        addSysLog("RX'd msg from BT. Mode 0");

                        if(msg.charAt(0) == CMD_BTN_STATE)
                        {
                            // got button update command
                            addSysLog("MCU Button update");
                            addSysLog("--MCU Android State: " + Integer.toString((int)msg.charAt(1))); // android state
                            addSysLog("--MCU Button State: " + Integer.toBinaryString((int)msg.charAt(2))); // button state
                            processRemoteDeviceButtons((byte)msg.charAt(1), (byte)msg.charAt(2));
                        }
                        else if(msg.charAt(0) == CMD_BAT_UPDATE)
                        {
                            // got button update command
                            addSysLog("MCU Battery update");
                            addSysLog("--MCU Android State: " + Integer.toString((int)msg.charAt(1))); // android state
                            addSysLog("--MCU Battery Percentage: " + Integer.toString((int)msg.charAt(2))); // battery percentage
                        }
                    }
                }
                else if(mode==1)
                {
                    if(msg!=null)
                    {
                        addSysLog("Mode 1 msg: " + msg);
                    }
                }
            }
            else if(intent.getAction().equals(BluetoothSerialService.BLUETOOTH_SERVICE_ALERT))
            {
                int id = intent.getIntExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_ALERT_CODE, 0);
                String message = intent.getStringExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_ALERT);

                if(id==BluetoothSerialService.ALERT_ID_STATE)
                {
                    try
                    {
                        state = Integer.parseInt(message);
                        if(state == 0)
                        {
                            prevState = 0;
                            if(timerRequest)
                            {
                                callConnection();
                                timerRequest = false;
                            }
                        }
                        else if(state == 3)
                        {
                            //we are connected// do something here
                            if(prevState == 0)
                            {
                                prevState = 3;

                                // Set Time
                                Calendar c = Calendar.getInstance();
                                BackgroundService.sendTimeUpdate(serviceContext, (byte)c.get(Calendar.HOUR_OF_DAY), (byte)c.get(Calendar.MINUTE));

                                // Set Player State to Error.. Will have to update by pause / play
                                BackgroundService.sendPlayerStateUpdate(serviceContext, (byte)42); // should force an error

                                // Check Mute
                                checkMuteStatusHelper();
                                BackgroundService.sendMuteStateUpdate(serviceContext, muteState);

                                // Set Waiting text
                                BackgroundService.sendLineUpdateIntent(serviceContext, "", (byte)1);
                                BackgroundService.sendLineUpdateIntent(serviceContext, "Waiting for", (byte)2);
                                BackgroundService.sendLineUpdateIntent(serviceContext, "    Meta Data", (byte)3);
                                BackgroundService.sendLineUpdateIntent(serviceContext, "", (byte)4);

                                // Set button text
                                BackgroundService.sendLineUpdateIntent(serviceContext, "Prev  | Next  | VolUp", (byte)5);
                                BackgroundService.sendLineUpdateIntent(serviceContext, "---------------------", (byte)6);
                                BackgroundService.sendLineUpdateIntent(serviceContext, "Mute  | Pl/Pa | VolDn", (byte)7);
                            }
                        }

                    }
                    catch(NumberFormatException nfe){}
                }
            }
        }//end on receive
    };//end broadcast receiver

    // Timer Task to handle Time Updates
    private TimerTask minuteTick = new TimerTask()
    {
        public void run()
        {
            if(state == 3) {
                Calendar c = Calendar.getInstance();
                addSysLog("Timer Task Minute Tick");
                byte h = (byte) c.get(Calendar.HOUR_OF_DAY);
                byte m = (byte) c.get(Calendar.MINUTE);
                BackgroundService.sendTimeUpdate(serviceContext, h, m);
            }
        }
    };

    // Timer Task to handle polling of Mute State
    private TimerTask checkMuteStatus = new TimerTask()
    {
        public void run()
        {
            if(state == 3) {
                checkMuteStatusHelper();
            }
        }
    };

    // Helper for checking mute state
    private void checkMuteStatusHelper()
    {
        if(audio != null) {
            addSysLog("Checking Mute Status");
            if (audio.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
                muteState = MUTE_ENABLED;
            } else {
                muteState = MUTE_DISABLED;
            }

            if(muteState != prevMuteState)
            {
                prevMuteState = muteState;
                BackgroundService.sendMuteStateUpdate(serviceContext, muteState);
            }
        }
    }

    // Connection State Handler
    private Runnable pollStatus = new Runnable()
    {
        public void run()
        {
            //send info
            timerRequest=true;
            requestConnected();
            connectionHandler.postDelayed(this, connectionPeriod);
        }
    };

    // Requests connection status
    private void requestConnected()
    {
        if(D)Log.d(TAG,"Automated: Requesting connection status");
        Intent pollIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_COMMAND);
        pollIntent.putExtra(BluetoothSerialService.COMMAND_PARAM_OP, BluetoothSerialService.COMMAND_GET_STATE);
        sendBroadcast(pollIntent);
    }

    // Requests to connect to BT Device
    private void callConnection()
    {
        if(D)Log.d(TAG,"Connection Call");
        //addSysLog("Automated: Connection Call");
        if(_myMac!=null)
        {
            if(D)Log.d(TAG,"Automated: Connection Request");
            Intent connectIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_COMMAND);
            connectIntent.putExtra(BluetoothSerialService.COMMAND_PARAM_OP, BluetoothSerialService.COMMAND_CONNECT);
            connectIntent.putExtra(BluetoothSerialService.COMMAND_PARAM_MAC, _myMac);
            sendBroadcast(connectIntent);
        }
    }

    // LogCat Helper Function  (useful if need to log elsewhere in addition to LogCat)
    private void addSysLog(String message)
    {
        if (D) Log.d(TAG, message);
    }

    // Button Logic
    private void processRemoteDeviceButtons(byte deviceState, byte buttonState)
    {
        byte processState; // To determine if changed
        if(prevButtonState != buttonState)
        {
            // Calculate change
            processState = (byte)(prevButtonState ^ buttonState);
            // for each button
            for(byte i = 0; i < 6; i++)
            {
                // check if state changed. Can account for held buttons with this method later
                // TODO Extend at a later date (hold.. additional modes etc..)
                if(((processState >> i) & (byte)1) == (byte)1)
                {
                    // button state changed (Buttons Active High)
                    if(((buttonState >> i) & (byte)1) == (byte)1)
                    {
                        // Button is Rising Edge (LOW to HIGH)
                        addSysLog("Button " + i + " is rising");
                    }
                    else
                    {
                        // Button is Falling Edge (HIGH to LOW)
                        addSysLog("Button " + i + " is falling");

                        if(i == 0)
                        {
                            // prev
                            addSysLog("Sending KeyEvent.KEYCODE_MEDIA_PREVIOUS");
                            //sendKeyEvent(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
                            try {
                                Runtime.getRuntime().exec(CMD_MEDIA_PREV);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (i == 1)
                        {
                            // next
                            addSysLog("Sending KeyEvent.KEYCODE_MEDIA_NEXT");
                            //sendKeyEvent(KeyEvent.KEYCODE_MEDIA_NEXT);
                            try {
                                Runtime.getRuntime().exec(CMD_MEDIA_NEXT);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (i == 2)
                        {
                            // vol up
                            addSysLog("Increasing STREAM_MUSIC Audio Level via AudioManager");
                            //sendKeyEvent(KeyEvent.KEYCODE_VOLUME_UP);
                            int volLevel = audio.getStreamVolume(AudioManager.STREAM_MUSIC) + 1;
                            addSysLog("STREAM_MUSIC volLevel: " + volLevel);
                            audio.setStreamVolume(AudioManager.STREAM_MUSIC, volLevel, 0);
                        }
                        else if (i == 3)
                        {
                            // mute
                            addSysLog("STREAM_MUSIC Audio Level Mute via AudioManager");
                            //sendKeyEvent(KeyEvent.KEYCODE_VOLUME_MUTE);
                            if(muteState == MUTE_DISABLED) {
                                audio.setStreamMute(AudioManager.STREAM_MUSIC, true);
                            }
                            else
                            {
                                audio.setStreamMute(AudioManager.STREAM_MUSIC, false);
                            }

                            checkMuteStatusHelper();
                        }
                        else if (i == 4)
                        {
                            // play / pause
                            addSysLog("Sending KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE");
                            //sendKeyEvent(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
                            try {
                                Runtime.getRuntime().exec(CMD_MEDIA_PAUSE_PLAY);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (i == 5)
                        {
                            // Vol dn
                            addSysLog("Decreasing STREAM_MUSIC Audio Level via AudioManager");
                            //sendKeyEvent(KeyEvent.KEYCODE_VOLUME_DOWN);
                            int volLevel = audio.getStreamVolume(AudioManager.STREAM_MUSIC) - 1;
                            if(volLevel < 0) volLevel = 0;
                            addSysLog("STREAM_MUSIC volLevel: " + volLevel);
                            audio.setStreamVolume(AudioManager.STREAM_MUSIC, volLevel, 0);
                        }
                    }
                }
                else
                {
                    addSysLog("Button " + i + " is unchanged");
                }
            }
            prevButtonState = buttonState;
        }
    }

    // Helper to Brodcast KeyEvents
    private void sendKeyEvent(int event)
    {
        Intent ke1 = new Intent(Intent.ACTION_MEDIA_BUTTON);
        Intent ke2 = new Intent(Intent.ACTION_MEDIA_BUTTON);
        synchronized (this) {
            ke1.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN, event));
            sendBroadcast(ke1);

            ke2.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, event));
            sendBroadcast(ke2);
        }
    }

    // Static function to update a line on the OLED (over BT)
    public static void sendLineUpdateIntent(Context context, String text, byte line_num)
    {
        char msg [] = text.toCharArray();
        int len = 21;

        if(msg.length <= 21)
            len = msg.length;

        Intent sendLineUpdateIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND);
        byte [] send = new byte[27];
        for(int i = 0; i<27; i++) {
            send[i] = 0;
        }
        send[0] = (byte)BackgroundService.CMD_SET_LINE;
        send[1] = line_num;
        for(int i = 0; i < len; i++)
        {
            send[i+2] = (byte)msg[i];
        }
        send[24] = 0xA;
        send[25] = 0xD;
        send[26] = 0x0;

        sendLineUpdateIntent.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND_DATA, send);
        context.sendBroadcast(sendLineUpdateIntent);
    }

    // Static function to update the time on the OLED (over BT)
    public static void sendTimeUpdate(Context context, byte hour, byte minute)
    {
        Intent sendTimeIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND);
        byte [] send = new byte[6];
        send[0] = (byte)BackgroundService.CMD_SET_TIME;
        send[1] = hour;
        send[2] = minute;
        send[3] = 0xA;
        send[4] = 0xD;
        send[5] = 0x0;

        sendTimeIntent.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND_DATA, send);
        context.sendBroadcast(sendTimeIntent);
    }

    // Static function to update the player state on the OLED (over BT)
    public static void sendPlayerStateUpdate(Context context, byte state)
    {
        Intent sendPlayerStateIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND);
        byte [] send = new byte[6];
        send[0] = (byte)BackgroundService.CMD_SET_PLAY;
        send[1] = state;
        send[2] = 0xA;
        send[3] = 0xD;
        send[4] = 0x0;

        sendPlayerStateIntent.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND_DATA, send);
        context.sendBroadcast(sendPlayerStateIntent);
    }

    // Static function to update the mute state on the OLED (over BT)
    public static void sendMuteStateUpdate(Context context, byte state)
    {
        Intent sendMuteStateIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND);
        byte [] send = new byte[6];
        send[0] = (byte)BackgroundService.CMD_SET_MUTE;
        send[1] = state;
        send[2] = 0xA;
        send[3] = 0xD;
        send[4] = 0x0;

        sendMuteStateIntent.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND_DATA, send);
        context.sendBroadcast(sendMuteStateIntent);
    }

    // Static function to request device battery status (over BT)
    public static void sendBatteryUpdateRequest(Context context)
    {
        Intent sendRequestBatteryUpdateIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND);
        byte [] send = new byte[4];
        send[0] = (byte)BackgroundService.CMD_GET_BAT;
        send[1] = 0xA;
        send[2] = 0xD;
        send[3] = 0x0;

        sendRequestBatteryUpdateIntent.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND_DATA, send);
        context.sendBroadcast(sendRequestBatteryUpdateIntent);
    }

    // Static function to update the state of the device (button context) (over BT)
    public static void sendStateUpdateRequest(Context context, byte state)
    {
        Intent sendStateUpdateIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND);
        byte [] send = new byte[5];
        send[0] = (byte)BackgroundService.CMD_SET_STATE;
        send[1] = state;
        send[2] = 0xA;
        send[3] = 0xD;
        send[4] = 0x0;

        sendStateUpdateIntent.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_TOSEND_DATA, send);
        context.sendBroadcast(sendStateUpdateIntent);
    }
}