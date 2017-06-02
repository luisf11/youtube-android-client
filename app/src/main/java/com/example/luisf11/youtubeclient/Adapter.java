package com.example.luisf11.youtubeclient;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Luis Fernando Pena on 6/2/2017.
 */

public class Adapter  extends ArrayAdapter{

    private Context context;
    private VideoItem[] videos;

    public Adapter(Activity context, VideoItem[]videos){
        super(context,videos);
        this.context = context;
        this.videos = videos;
    }


}
