package br.com.cinesky;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import br.com.cinesky.controls.FullscreenVideoView;

public class VideoActivity extends Activity {

    FullscreenVideoView videoView;
    Intent mIntent;

    public VideoActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.video_stream);

        mIntent = getIntent();

        videoView = (FullscreenVideoView)findViewById(R.id.fslFilme);
        videoView.setActivity(this);

        try {
            Uri videoUri = Uri.parse("https://openload.co/stream/L2jIQsJ7xBE~1495675175~131.0.0.0~63U111Xw?mime=true");
            videoView.setVideoURI(videoUri);
            String nomeFilme = mIntent.getStringExtra("nomeFilme");
            TextView tvNomeFilme = (TextView)findViewById(R.id.tvNomeFilme);
            tvNomeFilme.setText(nomeFilme);
        }catch (Exception e){
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}