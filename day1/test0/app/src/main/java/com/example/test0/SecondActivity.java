package com.example.test0;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class SecondActivity extends Activity {

    //final WebView web1 = findViewById(R.id.web1);
    //final SeekBar seekBar = findViewById(R.id.seekBar);
    //final ProgressBar progressBar = findViewById(R.id.progressBar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //web1.loadUrl("www.google.com");
    }

    /*private void bindViews(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressBar.setProgress(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                progressBar.setProgress(seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                progressBar.setProgress(seekBar.getProgress());
            }
        });
    }*/
}