package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class TwoNameActivity extends AppCompatActivity {

    TextInputEditText plyr1, plyr2;

    CharSequence player1= "Player 1";
    CharSequence player2= "Player 2";

    boolean selectedSinglePlayer=false;

    EditText Age1, Age2, MobileNo1, MobileNo2;


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

        Age1=(EditText)findViewById(R.id.aage1);
        MobileNo1=(EditText)findViewById(R.id.mmobile1);

        Age2=(EditText)findViewById(R.id.aage2);
        MobileNo2=(EditText)findViewById(R.id.mmobile2);




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


    public void ConnectingDatabase() {            // extends PlayerData<String s1, String n1 int a1>

        final String TAG = "";
        final String azuredatabaseName = "playerDatabase";
        final String cointainerName = "playerRecords";

        plyr1 = findViewById(R.id.playerone);
        plyr2 = findViewById(R.id.playerone);
        Age1 = (EditText) findViewById(R.id.aage);
        Age2 = (EditText) findViewById(R.id.aage);
        MobileNo1 = (EditText) findViewById(R.id.mmobile);
        MobileNo2 = (EditText) findViewById(R.id.mmobile);

        String finalName1 = plyr1.getText().toString();
        String finalName2 = plyr2.getText().toString();
        String finalMobile1 = MobileNo1.getText().toString();
        String finalMobile2 = MobileNo2.getText().toString();
        String finalAge1= Age1.getText().toString();
        String finalAge2 = Age2.getText().toString();

        int intAge1 = Integer.parseInt(finalAge1);
        int intAge2 = Integer.parseInt(finalAge2);

        PlayerData pData = new PlayerData(finalName1,finalName2,finalMobile1, finalMobile2, intAge1, intAge2);

    }

    public void onBackedPressed()
    {

        Intent i=new Intent(TwoNameActivity.this, MainActivity.class);
        startActivity(i);
    }
}