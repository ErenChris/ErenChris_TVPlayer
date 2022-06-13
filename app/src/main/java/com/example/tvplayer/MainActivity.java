package com.example.tvplayer;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener, SurfaceHolder.Callback {
    MediaPlayer TVPlayer = null;
    public SurfaceView sfv_show;
    public SurfaceHolder surfaceHolder;
    public Button btn_start;
    public Button btn_pause;
    public Button btn_stop;

    public void bindViews() {
        sfv_show = (SurfaceView) findViewById(R.id.sfv_show);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

        //初始化SurfaceHolder类，SurfaceView的控制器
        surfaceHolder = sfv_show.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(320, 220);   //显示的分辨率,不设置为视频默认
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                TVPlayer.start();
                break;
            case R.id.btn_pause:
                TVPlayer.pause();
                break;
            case R.id.btn_stop:
                TVPlayer.stop();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        TVPlayer = MediaPlayer.create(this, Uri.parse("http://39.134.65.177/PLTV/88888888/224/3221225812/1.m3u8"));
        TVPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        TVPlayer.setDisplay(surfaceHolder);    //设置显示视频显示在SurfaceView上
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (TVPlayer.isPlaying()) {
            TVPlayer.stop();
        }
        TVPlayer.release();
    }
}