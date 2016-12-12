package com.example.evan.movieapp;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

/**
 * Created by Evan on 12/11/2016.
 */

public class YoutubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    static final String API_KEY = "AIzaSyAWlv2SpKUdbtK7sPku-rBpCJQPcm7BAFE";
    String VIDEO_ID;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.youtube_view);

        VIDEO_ID = getIntent().getStringExtra("YOUTUBE_ID");

//        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
//        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
//add listeners to YouTubePlayer instance
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        //start buffering
        if(!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failure to Initialize!", Toast.LENGTH_LONG).show();
    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    //
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

}
