package com.example.luisf11.youtubeclient.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luisf11.youtubeclient.R;
import com.example.luisf11.youtubeclient.models.VideoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * Created by Luis Fernando Pena on 6/2/2017.
 */

public class VideoAdapter extends ArrayAdapter<VideoItem>{

    private ArrayList<VideoItem> list_videos;

   public static class ViewHolder{
       TextView title;
       TextView description;
       ImageView thumbnail;
   }

   public void addAll(ArrayList<VideoItem> list_videos){

       this.list_videos.addAll(list_videos);
       notifyDataSetChanged();
   }

public VideoAdapter(Context context, ArrayList<VideoItem> videos){
    super(context, R.layout.activity_search,videos);
    this.list_videos = videos;
}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VideoItem video = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_search,parent,false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.video_title);
            viewHolder.description = (TextView) convertView.findViewById(R.id.video_description);
            viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.video_thumbnail);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(video.getTitle());
        viewHolder.description.setText(video.getDescription());
        Picasso.with(this.getContext()).load(video.getThumbnailURL()).into(viewHolder.thumbnail);
        return convertView;
    }
}
