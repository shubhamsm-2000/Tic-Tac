package com.example.tictac;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      //  getActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(SplashActivity.this, R.color.colorPrimar));

        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        textView2.setAlpha(0f);
        textView3.setAlpha(0f);


        textView2.animate()
                .translationY(textView2.getHeight())
                .alpha(1f)
                .setStartDelay(1000)
                .setDuration(1200);

        textView3.animate()
                .translationY(textView3.getHeight())
                .alpha(1f)
                .setStartDelay(1500)
                .setDuration(1300);


        Thread thread=new Thread(){

            public void run(){

                try{
                    sleep(6000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{

                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}