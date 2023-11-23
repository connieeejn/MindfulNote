package com.example.mindfulnote.meditation;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mindfulnote.R;

public class VideoActivity extends Activity {

    public static final String EXTRA_VIDEO_URL = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = findViewById(R.id.videoView1);

        // Creating MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        // Get the video URL from the intent
        int position = getIntent().getIntExtra(EXTRA_VIDEO_URL, -1);
        int video;
        switch (position){
            case 0:
                video = R.raw.video1;
                break;
            case 1:
                video = R.raw.video2;
                break;
            case 2:
                video = R.raw.video3;
                break;
            case 3:
                video = R.raw.video4;
                break;
            default:
                video = 0;
                break;
        }

        // Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + video);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }
}
