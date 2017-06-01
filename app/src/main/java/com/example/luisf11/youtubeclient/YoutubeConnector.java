package com.example.luisf11.youtubeclient;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;

/**
 * Created by luisf11 on 28/05/17.
 */

public class YoutubeConnector {

    private YouTube youTube;
    private YouTube.Search.List query;

    public static final  String KEY = "AIzaSyCGTKqQDTW4Zqz_1_SPcY_XNgrod-YIr_M";

    public YoutubeConnector(Context context){
        youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {

            }
        }).setApplicationName(context.getString(R.string.app_name)).build();

        try {
            query = youTube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
            System.out.println("connected sucessfully");
        }catch (IOException e){
            Log.d("YC", "Could not initialize: "+ e.getMessage());

        }
    }
    public List<VideoItem> search(String keywords){
        query.setQ(keywords);
        try {
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();
            List<VideoItem> items = new ArrayList<>();

            for(SearchResult result : results){
                VideoItem item = new VideoItem();
                
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }
            return items;
        }catch (IOException e){
            Log.d("YC", "Could not search: "+ e);
            return null;
        }


    }
}
