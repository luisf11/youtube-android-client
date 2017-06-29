package com.example.luisf11.youtubeclient.view;



import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
    private String advActivity = "com.example.luisf11.youtubeclient.view.videoAdvertisementActivity";

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

//            Intent adv = new Intent(advActivity);
//            startActivity(adv);
            youTubePlayer.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,"Initialization Failed",Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {

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

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onResume() {
        super.onResume();
        //hide soft buttons

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
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
