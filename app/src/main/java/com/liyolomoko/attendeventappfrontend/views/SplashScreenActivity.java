package com.liyolomoko.attendeventappfrontend.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liyolomoko.attendeventappfrontend.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    Thread mythread = new Thread()
    {
        @Override
        public void run()
        {
            try{
            sleep(6000);
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    };
        mythread.start();
    }

}
