package com.example.mike.workoutaid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

    private String TAG;

    ComponentName bluetoothService = null;
    ComponentName backgroundService = null;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        TAG = this.getApplicationContext().getPackageName() + "ActMain";
        Log.d(TAG, "Activity Created");

        setContentView(R.layout.activity_main);

        Log.d(TAG,"Attempt to Start Bluetooth Service");
        bluetoothService = startService(new Intent(this,BluetoothSerialService.class));
        setupBluetooth();

        Log.d(TAG,"Attempt to Start Background Service");
        backgroundService = startService(new Intent(this,BackgroundService.class));
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Activity Destroyed");

        Log.d(TAG,"Stopping Background Service");
        stopService(new Intent(this,BackgroundService.class));

        Log.d(TAG,"Stopping Bluetooth Service");
        destroyBluetooth();
        stopService(new Intent(this,BluetoothSerialService.class));

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonHandler(View myView) {
        Intent i;
        if(myView.getId() == R.id.btnMute) {
            Log.d(TAG, "BTN_H: btnMute");
            BackgroundService.sendMuteStateUpdate(context, BackgroundService.MUTE_ENABLED);
        } else if(myView.getId() == R.id.btnUnmute) {
            Log.d(TAG, "BTN_H: btnUnmute");
            BackgroundService.sendMuteStateUpdate(context, BackgroundService.MUTE_DISABLED);
        } else if(myView.getId() == R.id.btnPlay) {
            Log.d(TAG, "BTN_H: btnPlay");
            BackgroundService.sendPlayerStateUpdate(context, BackgroundService.PLAYER_PLAYING);
        } else if(myView.getId() == R.id.btnPause) {
            Log.d(TAG, "BTN_H: btnPause");
            BackgroundService.sendPlayerStateUpdate(context, BackgroundService.PLAYER_PAUSED);
        } else if(myView.getId() == R.id.btnStop) {
            Log.d(TAG, "BTN_H: btnStop");
            BackgroundService.sendPlayerStateUpdate(context, BackgroundService.PLAYER_STOPPED);
        } else if(myView.getId() == R.id.btnTime) {
            Log.d(TAG, "BTN_H: btnTime");
            Calendar c = Calendar.getInstance();
            BackgroundService.sendTimeUpdate(context, (byte) c.get(Calendar.HOUR_OF_DAY), (byte) c.get(Calendar.MINUTE));
        } else if(myView.getId() == R.id.btnBattery) {
            Log.d(TAG, "BTN_H: btnBattery");
            BackgroundService.sendBatteryUpdateRequest(context);
        } else if(myView.getId() == R.id.btnState) {
            Log.d(TAG, "BTN_H: btnState");
            BackgroundService.sendStateUpdateRequest(context, (byte) 42);
        } else if(myView.getId() == R.id.btnLine1) {
            Log.d(TAG, "BTN_H: btnLine1");
            BackgroundService.sendLineUpdateIntent(context, "Line_1_Test", (byte) 1);
        } else if(myView.getId() == R.id.btnLine2) {
            Log.d(TAG, "BTN_H: btnLine2");
            BackgroundService.sendLineUpdateIntent(context, "Line_2_Test", (byte) 2);
        } else if(myView.getId() == R.id.btnLine3) {
            Log.d(TAG, "BTN_H: btnLine3");
            BackgroundService.sendLineUpdateIntent(context, "Line_3_Test", (byte) 3);
        } else if(myView.getId() == R.id.btnLine4) {
            Log.d(TAG, "BTN_H: btnLine4");
            BackgroundService.sendLineUpdateIntent(context, "Line_4_Test", (byte) 4);
        } else if(myView.getId() == R.id.btnLine5) {
            Log.d(TAG, "BTN_H: btnLine5");
            BackgroundService.sendLineUpdateIntent(context, "Line_5_Test", (byte) 5);
        } else if(myView.getId() == R.id.btnLine6) {
            Log.d(TAG, "BTN_H: btnLine6");
            BackgroundService.sendLineUpdateIntent(context, "Line_6_Test", (byte) 6);
        } else if(myView.getId() == R.id.btnLine7) {
            Log.d(TAG, "BTN_H: btnLine7");
            BackgroundService.sendLineUpdateIntent(context, "Line_7_Test", (byte) 7);
        } else if(myView.getId() == R.id.btn0) {
            Log.d(TAG, "BTN_H: btn0");
            buttonEmuHelper((byte)0b00000001);
            //dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PREVIOUS));
            //dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PREVIOUS));
        } else if(myView.getId() == R.id.btn1) {
            Log.d(TAG, "BTN_H: btn1");
            buttonEmuHelper((byte)0b00000010);
        } else if(myView.getId() == R.id.btn2) {
            Log.d(TAG, "BTN_H: btn2");
            buttonEmuHelper((byte)0b00000100);
        } else if(myView.getId() == R.id.btn3) {
            Log.d(TAG, "BTN_H: btn3");
            buttonEmuHelper((byte)0b00001000);
        } else if(myView.getId() == R.id.btn4) {
            Log.d(TAG, "BTN_H: btn4");
            buttonEmuHelper((byte)0b00010000);
        } else if(myView.getId() == R.id.btn5) {
            Log.d(TAG, "BTN_H: btn5");
            buttonEmuHelper((byte)0b00100000);
        } else {
            Log.d(TAG, "BTN_H: ERROR");
        }
    }

    private void buttonEmuHelper(byte btnState)
    {
        Intent i;
        char buf [] = new char[6];
        buf[0] = (char)BackgroundService.CMD_BTN_STATE;  buf[1] = 0;
        buf[2] = (char)btnState; // rising edge
        buf[3] = (char) 0xA; buf[4] = (char) 0xD; buf[5] = 0;

        i = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE);
        i.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_MESSAGE_CODE, BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE_TYPE_RX);
        i.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_MESSAGE, new String(buf));
        sendBroadcast(i);

        buf[2] = 0; // falling edge
        i = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE);
        i.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_MESSAGE_CODE, BluetoothSerialService.BLUETOOTH_SERVICE_MESSAGE_TYPE_RX);
        i.putExtra(BluetoothSerialService.BLUETOOTH_SERVICE_PARAM_MESSAGE, new String(buf));
        sendBroadcast(i);
    }

    private void setupBluetooth()
    {
        Intent startIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_COMMAND);
        startIntent.putExtra(BluetoothSerialService.COMMAND_PARAM_OP, BluetoothSerialService.COMMAND_START);
        sendBroadcast(startIntent);
    }

    private void destroyBluetooth()
    {
        Intent stopIntent = new Intent(BluetoothSerialService.BLUETOOTH_SERVICE_COMMAND);
        stopIntent.putExtra(BluetoothSerialService.COMMAND_PARAM_OP, BluetoothSerialService.COMMAND_STOP);
        sendBroadcast(stopIntent);
    }
}
