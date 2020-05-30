package com.flatfisher.dialogflowchatbotsample;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class BookActivityWaiting extends AppCompatActivity {


    ProgressBar splashProgress;
    int SPLASH_TIME = 5000;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_book);

        splashProgress = findViewById(R.id.splashProgress);
        playProgress();
        setTitle("Trust&GO");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(BookActivityWaiting.this, homeActivity.class);
                startActivity(homeIntent);
                finish();

            }
        }, SPLASH_TIME);
    }

    //Method to run progress bar for 5 seconds
    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(4000)
                .start();
    }
}
