package com.example.luisf11.youtubeclient.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.luisf11.youtubeclient.R;
import com.example.luisf11.youtubeclient.models.VideoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Luis Fernando Pena on 6/2/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    private List<VideoItem> list_videos;

    public VideoAdapter(List<VideoItem> list_videos) {
        this.list_videos = list_videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
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

    public static class ViewHolder extends RecyclerView.ViewHolder{
       TextView title;
       TextView description;
       ImageView thumbnail;

       public ViewHolder(View itemView) {
           super(itemView);
           title = (TextView) itemView.findViewById(R.id.video_title);
           description = (TextView) itemView.findViewById(R.id.video_description);
           thumbnail = (ImageView) itemView.findViewById(R.id.video_thumbnail);
       }
   }



//public VideoAdapter(Context context, ArrayList<VideoItem> videos){
//    super(context, R.layout.activity_search,videos);
//    this.list_videos = videos;
//}
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        VideoItem video = getItem(position);
//
//        ViewHolder viewHolder;
//        if (convertView == null){
//            viewHolder = new ViewHolder();
//
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_search,parent,false);
//
//            viewHolder.title = (TextView) convertView.findViewById(R.id.video_title);
//            viewHolder.description = (TextView) convertView.findViewById(R.id.video_description);
//            viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.video_thumbnail);
//
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.title.setText(video.getTitle());
//        viewHolder.description.setText(video.getDescription());
//        Picasso.with(this.getContext()).load(video.getThumbnailURL()).into(viewHolder.thumbnail);
//        return convertView;
//    }
}
