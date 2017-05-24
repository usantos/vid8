package br.com.cinesky;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.io.IOException;

import br.com.cinesky.controls.FullscreenVideoLayout;

public class VideoActivity extends AppCompatActivity {

    FullscreenVideoLayout videoLayout;
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.video_stream);

        videoLayout = (FullscreenVideoLayout)findViewById(R.id.videoview);
        videoLayout.setActivity(this);

        try {
            Uri videoUri = Uri.parse("https://openload.co/stream/L2jIQsJ7xBE~1495675175~131.0.0.0~63U111Xw?mime=true");
            videoLayout.setVideoURI(videoUri);
            videoLayout.start();
            String nomeFilme = intent.getStringExtra("nomeFilme");
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