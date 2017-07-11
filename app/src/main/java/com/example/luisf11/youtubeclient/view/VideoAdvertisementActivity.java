package com.example.luisf11.youtubeclient.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;


import com.example.luisf11.youtubeclient.R;

/**
 * Created by Luis Fernando Pena on 7/10/2017.
 */

public class VideoAdvertisementActivity extends Activity {

   private  String PlayerActivity = "com.example.luisf11.youtubeclient.view.PlayerActivity";

    @Override
    public void onCreate( Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.video_advertisement);

        Toast.makeText(this,"Initialization adv ",Toast.LENGTH_LONG).show();

        Log.i("debb","hey hey listen");

    }
}
