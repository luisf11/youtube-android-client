package com.example.luisf11.youtubeclient.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luisf11.youtubeclient.R;
import com.example.luisf11.youtubeclient.adapters.AdvertisementAdapter;
import com.example.luisf11.youtubeclient.adapters.VideoAdapter;
import com.example.luisf11.youtubeclient.models.Advertisement;
import com.example.luisf11.youtubeclient.models.ServerConfig;
import com.example.luisf11.youtubeclient.models.VideoItem;
import com.example.luisf11.youtubeclient.utils.XmlManager;
import com.example.luisf11.youtubeclient.utils.YoutubeConnector;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchActivity extends Activity {


    private EditText searchInput;

    private Button searchButton;
    private VideoAdapter videoAdapter;
    private Handler handler;
    private EditText txtIp;
    private EditText txtPort;
    private EditText txtPrefix;
    private XmlManager xmlManager;
    private RecyclerView recyclerView;


//    private List<Advertisement> advertisementImages;
    private List<VideoItem> searchResults;
    List<Advertisement> advertisementImages = new ArrayList<>(Arrays.asList(
            new Advertisement("/storage/ext_sd/ivans/Images/1.png"),
            new Advertisement("https://tse4.mm.bing.net/th?id=ORT.TH_470633631&pid=1.12&eid=G.470633631"),
            new Advertisement("https://www.w3schools.com/css/trolltunga.jpg"),
            new Advertisement("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTn4ufO0l4WyRxifUAKWIdOM0wjhGLv3-mG-ldJDpnZzUSViXEq"),
            new Advertisement("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9CRhxww7b_RxBGEXeBnIPpScSeQLuFueRylvWsRhsefRpP2HT")
    ));

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchInput = (EditText) findViewById(R.id.search_input);
        recyclerView = (RecyclerView) findViewById(R.id.videos_found);
        searchButton = (Button) findViewById(R.id.button_search);

//        txtIp.setText("192.168.0.5");
        txtIp = (EditText)findViewById(R.id.text_ip);
        txtPort = (EditText)findViewById(R.id.text_port);
        txtPrefix = (EditText)findViewById(R.id.text_prefix);

        handler = new Handler();


        //dialog to show xml configuration
<<<<<<< HEAD
//        showDialog();
=======
        showDialog();
>>>>>>> aff9eecbe12244fa70cb029dda664a5d362764d4

        //advertisement carusel
        setAdvertisement();
        //listener of search box

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
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        updateVideosFound();

                    }
                });

            }
        }.start();
    }

    private void setAdvertisement(){

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
<<<<<<< HEAD
//        final AdvertisementAdapter adapter = new AdvertisementAdapter(advertisementImages,this);
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int lastItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
//                if(lastItem == linearLayoutManager.getItemCount()-1){
//                    mhandler.removeCallbacks(SCROLLING_RUNNABLE);
//                    Handler postHandler = new Handler();
//                    postHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            recyclerView.setAdapter(null);
//                            recyclerView.setAdapter(adapter);
//                            mhandler.postDelayed(SCROLLING_RUNNABLE,2000);
//                        }
//                    },2000);
//                }
//            }
//        });
//        mhandler.postDelayed(SCROLLING_RUNNABLE,2000);
        recyclerView.setLayoutManager(linearLayoutManager);
        AdvertisementAdapter adapter = new AdvertisementAdapter(advertisementImages,this);
//        recyclerView.smoothScrollToPosition(1);
=======
        recyclerView.setLayoutManager(linearLayoutManager);
        AdvertisementAdapter adapter = new AdvertisementAdapter(advertisementImages,this);
>>>>>>> aff9eecbe12244fa70cb029dda664a5d362764d4
        recyclerView.setAdapter(adapter);

    }

    private void updateVideosFound(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        VideoAdapter adapter = new VideoAdapter(searchResults,this);
        recyclerView.setAdapter(adapter);

    }

    public void showDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.server_config,null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Server Config");

        dialogBuilder.setPositiveButton("Done",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ServerConfig config = new ServerConfig();
                xmlManager = new XmlManager();

//                String ip = txtIp.getText().toString();
//                String port =txtPort.getText().toString();
//                String prefix = txtPrefix.getText().toString();

                String ip = "123.125.2.2";
                String port ="8008";
                String prefix = "br";


                config.setIp(ip);
                config.setPort(port);
                config.setPrefix(prefix);
                Log.i("logger","ip: "+ ip);
                Log.i("logger","port: "+ port);
                Log.i("logger","prefix: "+ prefix);

                xmlManager.xmlWriter(config,getApplicationContext());


            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
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

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //hide soft buttons

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
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
