package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Timer;
import java.util.TimerTask;

public class ChooseActivity extends AppCompatActivity {

    CharSequence player1="Player 1";
    CharSequence player2="Player 2";

    boolean player1ax;

    boolean selectedSinglePlayer;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        try {

            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

            AdView adView = new AdView(this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");


            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Code to be executed when an ad request fails.
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            });
        }
        catch(Exception e){

        }


        CharSequence[] players = getIntent().getCharSequenceArrayExtra("playersName");
        player1 = players[0];
        player2 = players[1];

        TextView textView = (TextView) findViewById(R.id.text3);

        selectedSinglePlayer = getIntent().getBooleanExtra("selectedPlayer", true);

        if (!selectedSinglePlayer)
            textView.setText(player1 + " Choose Your Element");


        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);

        imageView1.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));
        imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));

        final RadioButton r1 = findViewById(R.id.player1o);
        final RadioButton r2 = findViewById(R.id.player1x);

        final int textColor = Color.parseColor("#e5e9ea");
        final int textColorBlue = Color.parseColor("#3b7df8");


        r1.post(new Runnable() {
            @Override
            public void run() {
                if (r1.isChecked()) {
                    r1.setButtonTintList(ColorStateList.valueOf(textColorBlue));


                } else {
                    r1.setButtonTintList(ColorStateList.valueOf(textColor));
                }
                r1.postDelayed(this, 10);
            }
        });

        r2.post(new Runnable() {
            @Override
            public void run() {
                if (r2.isChecked()) {
                    r2.setButtonTintList(ColorStateList.valueOf(textColorBlue));
                } else {
                    r2.setButtonTintList(ColorStateList.valueOf(textColor));

                }
                r2.postDelayed(this, 10);
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2.setChecked(false);
                r1.setChecked(true);

                imageView1.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));
                player1ax = true;
                imageView1.setImageResource(R.drawable.zero);
                imageView2.setImageResource(R.drawable.cross);
                imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));


            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setChecked(false);
                r2.setChecked(true);
                player1ax = false;
                imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));
                imageView1.setImageResource(R.drawable.zero);
                imageView2.setImageResource(R.drawable.cross);
                imageView1.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));

            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2.setChecked(false);
                player1ax = true;
                imageView1.setImageResource(R.drawable.zero);
                imageView2.setImageResource(R.drawable.cross);
                imageView1.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));
                imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));


            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setChecked(false);
                player1ax = false;
                imageView1.setImageResource(R.drawable.zero);
                imageView2.setImageResource(R.drawable.cross);
                imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));
                imageView1.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));


            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


                if (r1.isChecked() || r2.isChecked()) {

                    Button ds = findViewById(R.id.button);
                    ds.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(ChooseActivity.this, GameActivity.class);
                            CharSequence[] players = {player1, player2};
                            i.putExtra("playersName", players);
                            i.putExtra("player1ax", player1ax);
                            i.putExtra("selectedPlayer", selectedSinglePlayer);
                            startActivity(i);
                        }
                    });
                }
            }


        }, 0, 20);//put here time 1000 milliseconds = 1 second

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
        startActivity(intent);
    }
}



