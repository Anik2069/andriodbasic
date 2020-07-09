package com.example.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView v1= (VideoView) findViewById(R.id.videoView);
//video is remove please update its neccessary
     //   v1.setVideoPath("android.resource://"+ getPackageName() +"/" + R.raw.video1);

        MediaController m1 =new MediaController(this);

        m1.setAnchorView(v1);
        v1.setMediaController(m1);
        v1.start();

    }
}
