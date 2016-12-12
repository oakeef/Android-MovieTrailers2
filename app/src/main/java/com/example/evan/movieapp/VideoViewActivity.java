package com.example.evan.movieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Evan on 12/11/2016.
 */

public class VideoViewActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);

        String trailerUrl = getIntent().getStringExtra("VIDEO_URL");

        videoView = (VideoView) findViewById(R.id.vv1);
        videoView.setVideoURI(Uri.parse(trailerUrl));
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
    }
}
