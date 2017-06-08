package com.example.luisf11.youtubeclient.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luisf11.youtubeclient.R;
import com.example.luisf11.youtubeclient.adapters.VideoAdapter;
import com.example.luisf11.youtubeclient.models.VideoItem;
import com.example.luisf11.youtubeclient.utils.YoutubeConnector;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;



public class SearchActivity extends Activity {


    private EditText searchInput;
    private ListView videosFound;
    private Button searchButton;
    private VideoAdapter videoAdapter;
    private Handler handler;

    private ArrayList<VideoItem> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchInput = (EditText) findViewById(R.id.search_input);
        videosFound = (ListView) findViewById(R.id.videos_found);
        searchButton = (Button) findViewById(R.id.button_search);
        handler = new Handler();

        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

               if (actionId == EditorInfo.IME_ACTION_DONE){
                   searchOnYoutube(v.getText().toString());
                   return false;
               }
               return true;
            }
        });


        addClickListener();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               searchOnYoutube(searchInput.getText().toString());
            }
        });



    }

    private void searchOnYoutube(final String keywords){
        new Thread(){
            @Override
            public void run() {
                YoutubeConnector yc = new YoutubeConnector(SearchActivity.this);
                searchResults = yc.search(keywords);
//                runOnUiThread(new Runnable(){
//                    @Override
//                    public void run() {
//                        updateVideosFound();
//
//                    }
//                });
                handler.post(new Runnable(){
                    @Override
                        public void run() {
                            updateVideosFound();

                                 }
                             });
            }
        }.start();
    }

    private void updateVideosFound(){
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.video_item, searchResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                TextView description = (TextView)convertView.findViewById(R.id.video_description);

                VideoItem searchResult = searchResults.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };
        videosFound.setAdapter(adapter);
//
    }
    private void addClickListener(){
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(),PlayerActivity.class);
                intent.putExtra("VIDEO_ID",searchResults.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            trimCache(this);
            // Toast.makeText(this,"onDestroy " ,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // The directory is now empty so delete it
        return dir.delete();
    }
}
