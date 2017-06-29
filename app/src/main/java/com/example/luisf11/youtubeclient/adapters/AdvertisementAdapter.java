package com.example.luisf11.youtubeclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.luisf11.youtubeclient.R;
import com.example.luisf11.youtubeclient.models.Advertisement;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Luis Fernando Pena on 6/29/2017.
 */

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.ViewHolder> {
    private List<Advertisement> advertisementList;
    Context context;

    public AdvertisementAdapter(List<Advertisement> advertisementList, Context context) {
        this.advertisementList = advertisementList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_advertisement,parent,false);
        ViewHolder holder = new ViewHolder(v,context);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.image.getContext();
        Picasso.with(context).load(advertisementList.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return advertisementList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        Context context;

        public ViewHolder(View itemView,Context context) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.advertisement);
            this.context = context;
        }
    }
}
