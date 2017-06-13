package com.example.luisf11.youtubeclient.view;



import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.luisf11.youtubeclient.R;
import com.example.luisf11.youtubeclient.utils.YoutubeConnector;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{


    private YouTubePlayerView playerView;
    private AudioManager audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerView = (YouTubePlayerView) findViewById(R.id.player_view);
        playerView.initialize(YoutubeConnector.KEY,this);
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b){
            youTubePlayer.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,"Initialization Failed",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        Log.i("hey listen","hey Listen");
        int AXIS_VSCROLL =0;
//        event.getAxisValue(AXIS_VSCROLL);
        if ( event.getAxisValue(MotionEvent.AXIS_VSCROLL)==1){
            audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
            return true;
        }else if( event.getAxisValue(MotionEvent.AXIS_VSCROLL)==-1){
            audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
            return true;
        }
      //  return  false;

        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        int AXIS_VSCROLL =0;
//        event.getAxisValue(AXIS_VSCROLL);
        if ( event.getAxisValue(AXIS_VSCROLL)==1){
            audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
            return true;
        }else if( event.getAxisValue(AXIS_VSCROLL)==-1){
            audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
            return true;
        }
        return  false;
//        return super.onTrackballEvent(event);
    }
}
