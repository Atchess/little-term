package com.bytedance.videoplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView videoView = findViewById(R.id.video_container);
        final ProgressBar progressBar = findViewById(R.id.progress_bar);
        videoView.setMediaController(new MediaController(this));
        Intent intent = getIntent();
        String str = intent.getStringExtra("url");
        if (str.equals("local")) {
            videoView.setVideoURI(Uri.parse("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4"));
            Log.i("TAG","local");
        }
        else {
            videoView.setVideoURI(Uri.parse(str));
            Log.i("TAG","player:"+str);
        }
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
            }
        });
        progressBar.setVisibility(View.VISIBLE);
    }
}