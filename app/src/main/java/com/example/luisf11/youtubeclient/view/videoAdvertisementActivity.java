package com.example.luisf11.youtubeclient.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;


import com.example.luisf11.youtubeclient.R;

/**
 * Created by Luis Fernando Pena on 7/10/2017.
 */

public class videoAdvertisementActivity extends Activity {

   private  String PlayerActivity = "com.example.luisf11.youtubeclient.view.PlayerActivity";

    @Override
    public void onCreate( Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.video_advertisement);

//        Thread advTimer = new Thread(){
//            public void run(){
//                try{
//                    sleep(2500);
//                    Intent playerIntent = new Intent(PlayerActivity);
//                    startActivity(playerIntent);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }finally {
//                    finish();
//                }
//            }
//        };
//        advTimer.start();

    }
}
