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


public class SingleName extends AppCompatActivity {

    TextInputEditText plyr1;

    CharSequence player1="1";
    CharSequence player2="2";

    EditText age, mobileNo;

     public boolean selectedSinglePlayer=true;
     public int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_name);

        plyr1 = (TextInputEditText) findViewById(R.id.playerone);
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


/*
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                length = plyr1.length();


            }
        }, 0, 2);//put here time 1000 milliseconds = 1 second


    //    TextView nameText= findViewById(R.id.playerone);
    //    int length= nameText.getText().toString().length();

     //   new Timer().scheduleAtFixedRate(new TimerTask() {
     //       @Override
     //       public void run() {

  */    //      if (length > 2) {
                Button singleNameButton = (Button) findViewById(R.id.singleButton);

                //  singleNameButton.setEnabled(true);

                singleNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence[] players = {player1, player2};

                        Intent singlePlayerIntent = new Intent(SingleName.this, ChooseActivity.class);
                        singlePlayerIntent.putExtra("playersName", players);
                        singlePlayerIntent.putExtra("selectedPlayer", selectedSinglePlayer);

                        startActivity(singlePlayerIntent);
                    }
                });

  //      }, 0, 20);//put here time 1000 milliseconds = 1 second

}                   public void ConnectingDatabase() {            // extends PlayerData<String s1, String n1 int a1>

                    final String TAG = "";
                    final String azuredatabaseName = "playerDatabase";
                    final String cointainerName = "playerRecords";

                 plyr1=  findViewById(R.id.playerone);
                 age = (EditText) findViewById(R.id.aage);
                 mobileNo = (EditText) findViewById(R.id.mmobile);


                 String finalName=plyr1.getText().toString();
                 String finalAge=age.getText().toString();
                 String finalMobile=age.getText().toString();

                 int intAge= Integer.parseInt(finalAge);

                 PlayerData pData= new PlayerData(finalName, finalMobile,intAge);




    }



    @Override
                 public void onBackPressed() {

                 Intent i=new Intent(SingleName.this, MainActivity.class);
                 startActivity(i);
        }
}

