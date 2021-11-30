package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class settingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        RelativeLayout r4 = findViewById(R.id.r4);
        r4.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Toast.makeText(getApplicationContext(), "Can't load ad", Toast.LENGTH_SHORT).show();
           }
    }
        );


        RelativeLayout r5 = findViewById(R.id.r5);
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating();
            }
        });

        RelativeLayout r6 = findViewById(R.id.r6);
        r6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               feedback();
            }
        });

        RelativeLayout r7 = findViewById(R.id.r7);
        r7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Help();
            }
        });

    }

        public void rating(){

        Uri uri=Uri.parse("market://details?id="+getApplicationContext().getPackageName());
        Intent intent =new Intent(Intent.ACTION_VIEW, uri);

        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |  Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        try{
            startActivity(intent);
        }

        catch (ActivityNotFoundException e){
            Intent intent1=new Intent(Intent.ACTION_VIEW, uri.parse(
                    "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName()));

            startActivity(intent1);
        }


        }

        public void feedback(){

            Intent intent=new Intent(Intent.ACTION_SEND);
            String[] recipients={"kumarshubhamsm4@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Tic Tac Toe Feedbacks");
            intent.putExtra(Intent.EXTRA_CC,"kumarshubhamsm4@gmail.com");

            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));


    }

        public void Help(){

            Intent intent=new Intent(Intent.ACTION_SEND);
            String[] recipients={"kumarshubhamsm4@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Tic Tac Toe Help");
            intent.putExtra(Intent.EXTRA_CC,"kumarshubhamsm4@gmail.com");

            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));




        }

    @Override
    public void onBackPressed()
    {
        Intent intentBack=new Intent(settingActivity.this, MainActivity.class);
        startActivity(intentBack);
    }

}