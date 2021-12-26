package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SingleName extends AppCompatActivity {

    TextInputEditText plyr1;

    CharSequence player1="1";
    CharSequence player2="2";

    EditText age;
    EditText secretCode;

     public boolean selectedSinglePlayer=true;
     public int length;

     public Connection con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_name);

        plyr1 = findViewById(R.id.playerone);
        age = (EditText) findViewById(R.id.aage);


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

        Button button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                importingDatabaseItem imp=new importingDatabaseItem();      //**********
                imp.execute();
            }
        });


        Button button2 = (Button) findViewById(R.id.singleButton);

        //  singleNameButton.setEnabled(true);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] players = {player1, player2};


                Intent singlePlayerIntent = new Intent(SingleName.this, ChooseActivity.class);
                singlePlayerIntent.putExtra("playersName", players);
                singlePlayerIntent.putExtra("selectedPlayer", selectedSinglePlayer);

                startActivity(singlePlayerIntent);


            }
        });

    }  //      }, 0, 20);//put here time 1000 milliseconds = 1 second


    public class importingDatabaseItem extends AsyncTask<String, String, String>{

        String z="";
        Boolean isSuccess=false;
        String name1="";

        protected void onPreExecute(){

        }
        @Override
        protected void onPostExecute(String r){

            Toast.makeText(SingleName.this, r, Toast.LENGTH_LONG).show();
            if(isSuccess)
            {
              //  userdata=(Textview) findViewById(R.id.userid);
              //   userdata.setText(name1);

                secretCode = (EditText) findViewById(R.id.secretCode);
                secretCode.setText(name1);


            }
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                con=connectionclass();
                if(con==null)
                {
                    z="Check Your Internet Connection";
                }
                else{
                    String query="Select * from Users";
                    Statement stmt=con.createStatement();
                    ResultSet resultSet=stmt.executeQuery(query);

                    if(resultSet.next()){
                        name1= resultSet.getString("UserName");        //****** getString("secret code")
                        z="Query Successful";
                        isSuccess=true;
                        con.close();
                    }
                    else{
                        z="Error";
                        isSuccess=false;
                    }
                }
            }
            catch(Exception ex){
                isSuccess=false;
                z=ex.getMessage();
                Log.d("sql error",z);
            }
            return z;
        }
    }
    @SuppressLint({"NewApi", "AuthLeak"})
    public Connection connectionclass(){

    //    ThreadPolicy policy = new StrictMode.ThreadPolicy().Builder().permitAll().build();
    //    StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String ConnectionURL=null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://tictacserver.database.windows.net:1433;DatabaseName=TicTacProject;user=TicTacServerAdmin@tictacserver;password={******};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection= DriverManager.getConnection(ConnectionURL);
        }

        catch(SQLException se){
            Log.e("Error 1",se.getMessage());
        }

        catch(ClassNotFoundException ce){
            Log.e("Error 2",ce.getMessage());
        }
        catch (Exception ex){
            Log.e("Error 3",ex.getMessage());
        }

        return connection;
    }

  /*  public void CheckLogin()

           {            // extends PlayerData

                     String TAG = "";
                     String azuredatabaseName = "playerDatabase";
                     String cointainerName = "playerRecords";




                 String finalName=plyr1.getText().toString();
                 String finalAge=age.getText().toString();
                 String finalMobile=age.getText().toString();

                 int intAge= Integer.parseInt(finalAge);

                 PlayerData pData= new PlayerData(finalName, finalMobile,intAge);




    }     */



    @Override
                 public void onBackPressed() {

                 Intent i=new Intent(SingleName.this, MainActivity.class);
                 startActivity(i);
        }
}

