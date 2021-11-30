package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class TwoNameActivity extends AppCompatActivity {

    TextInputEditText plyr1, plyr2;

    CharSequence player1= "Player 1";
    CharSequence player2= "Player 2";

    boolean selectedSinglePlayer=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_name);

        plyr1 = (TextInputEditText) findViewById(R.id.playerone1);
        plyr1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player1 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        plyr2 = (TextInputEditText) findViewById(R.id.playerone2);
        plyr2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence[] players= {player1, player2};
                Intent i=new Intent(TwoNameActivity.this, ChooseActivity.class);
                i.putExtra("selectedPlayer", selectedSinglePlayer);
                i.putExtra("playersName", players);

                startActivity(i);
            }
        });

    }

      public void onBackedPressed()
    {

        Intent i=new Intent(TwoNameActivity.this, MainActivity.class);
        startActivity(i);
    }
}