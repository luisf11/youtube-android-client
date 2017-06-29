package com.example.luisf11.youtubeclient.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.luisf11.youtubeclient.R;
import com.example.luisf11.youtubeclient.models.VideoItem;
import com.example.luisf11.youtubeclient.view.PlayerActivity;
import com.google.api.services.youtube.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Luis Fernando Pena on 6/2/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<VideoItem> list_videos;
    Context context;

    public VideoAdapter(List<VideoItem> list_videos,Context context) {
        this.list_videos = list_videos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        ViewHolder holder = new ViewHolder(v,context,list_videos);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(list_videos.get(position).getTitle());
        holder.description.setText(list_videos.get(position).getDescription());
        Context context = holder.thumbnail.getContext();
        Picasso.with(context).load(list_videos.get(position).getThumbnailURL()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return list_videos.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       TextView title;
       TextView description;
       ImageView thumbnail;
        List<VideoItem> list_videos  = new ArrayList<>();
        Context context;

       public ViewHolder(View itemView, Context context, List<VideoItem> list_videos) {
           super(itemView);
           this.context = context;
           this.list_videos = list_videos;
           itemView.setOnClickListener(this);
           title = (TextView) itemView.findViewById(R.id.video_title);
           description = (TextView) itemView.findViewById(R.id.video_description);
           thumbnail = (ImageView) itemView.findViewById(R.id.video_thumbnail);
       }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            VideoItem video = this.list_videos.get(position);
            Intent intent = new Intent(this.context, PlayerActivity.class);
            intent.putExtra("VIDEO_ID",video.getId());
            this.context.startActivity(intent);

        }
    }

}
