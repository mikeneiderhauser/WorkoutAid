package com.example.mike.workoutaid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SpotifyBCR extends BroadcastReceiver {
    static final class BroadcastTypes
    {
        static final String SPOTIFY_PACKAGE = "com.spotify.music";
        static final String PLAYBACK_STATE_CHANGED = SPOTIFY_PACKAGE + ".playbackstatechanged";
        static final String QUEUE_CHANGED = SPOTIFY_PACKAGE + ".queuechanged";
        static final String METADATA_CHANGED = SPOTIFY_PACKAGE + ".metadatachanged";
    }

    private static final String TAG = "SpotifyBCR.Spotify";

    @Override
    public void onReceive(Context context, Intent intent) {
        // This is sent with all broadcasts, regardless of type. The value is taken from
        // System.currentTimeMillis(), which you can compare to in order to determine how
        // old the event is.
        long timeSentInMs = intent.getLongExtra("timeSent", 0L);

        String action = intent.getAction();

        if (action.equals(BroadcastTypes.METADATA_CHANGED)) {
            String trackId = intent.getStringExtra("id");
            String artistName = intent.getStringExtra("artist");
            String albumName = intent.getStringExtra("album");
            String trackName = intent.getStringExtra("track");

            int trackLengthInSec = intent.getIntExtra("length", 0);
            String trackLength = "" + trackLengthInSec/60 + ":";
            if(trackLengthInSec%60 < 10) trackLength += "0";
            trackLength += trackLengthInSec%60;

            Log.d(TAG, trackName + "<.>" + albumName + "<.>" + artistName + "<.>" + trackLength);
            // Do something with extracted information...

            BackgroundService.sendLineUpdateIntent(context, trackName, (byte)1);
            BackgroundService.sendLineUpdateIntent(context, artistName, (byte)2);
            BackgroundService.sendLineUpdateIntent(context, albumName, (byte)3);
            BackgroundService.sendLineUpdateIntent(context, trackLength, (byte)4);
            BackgroundService.sendPlayerStateUpdate(context, BackgroundService.PLAYER_PLAYING);

        } else if (action.equals(BroadcastTypes.PLAYBACK_STATE_CHANGED)) {
            boolean playing = intent.getBooleanExtra("playing", false);
            int positionInMs = intent.getIntExtra("playbackPosition", 0);
            if(!playing) {
                Log.d(TAG, "Player State: paused");
                BackgroundService.sendPlayerStateUpdate(context, BackgroundService.PLAYER_PAUSED);
            } else {
                Log.d(TAG, "Player State: playing");
                BackgroundService.sendPlayerStateUpdate(context, BackgroundService.PLAYER_PLAYING);
            }
            // Do something with extracted information
        } else if (action.equals(BroadcastTypes.QUEUE_CHANGED)) {
            // Sent only as a notification, your app may want to respond accordingly.
            Log.d(TAG, "Queue: Playback queue changed");
        }
    }
}