package br.com.cinesky;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.cinesky.controls.FullscreenVideoLayout;
import br.com.cinesky.controls.FullscreenVideoView;

public class VideoActivity extends Activity {

    FullscreenVideoLayout videoView;
    Intent mIntent;

    public VideoActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.video_stream);

        mIntent = getIntent();

        FrameLayout frmFilmeAcao = (FrameLayout) findViewById(R.id.frmFilmeAcao);
        frmFilmeAcao.bringToFront();

        ImageView btnVoltar = (ImageView) findViewById(R.id.btnVoltar);
        btnVoltar.bringToFront();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.this.finish();
            }
        });

        videoView = (FullscreenVideoLayout) findViewById(R.id.fslFilme);
        videoView.setActivity(this);
        videoView.setFullscreen(false);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        try {
            Uri videoUri = Uri.parse("https://www.quirksmode.org/html5/videos/big_buck_bunny.mp4");
            videoView.setVideoURI(videoUri);
            videoView.setShouldAutoplay(true);
            videoView.performClick();
            videoView.findViewById(R.id.vcv_img_fullscreen).setVisibility(View.INVISIBLE);
            videoView.findViewById(R.id.vcv_seekbar).setBackgroundColor(Color.TRANSPARENT);
            videoView.findViewById(R.id.rel_videocontrols).setBackgroundColor(Color.TRANSPARENT);

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