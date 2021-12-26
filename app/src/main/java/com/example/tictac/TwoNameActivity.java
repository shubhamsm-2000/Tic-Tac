package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TwoNameActivity extends AppCompatActivity {

    TextInputEditText plyr1, plyr2;

    CharSequence player1= "Player 1";
    CharSequence player2= "Player 2";

    boolean selectedSinglePlayer=false;

    EditText Age1, Age2, secretCode1, secretCode2;

    Button button1, button2;

    public Connection con;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_name);

        Age1=(EditText)findViewById(R.id.aage1);
        Age2=(EditText)findViewById(R.id.aage2);

        button1=(Button)findViewById(R.id.button2);
        button2=(Button)findViewById(R.id.button2);

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



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                importingDatabaseItem imp=new importingDatabaseItem();      //**********
                imp.execute();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
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

/*
    public void ConnectingDatabase() {            // extends PlayerData<String s1, String n1 int a1>

        final String TAG = "";
        final String azuredatabaseName = "playerDatabase";
        final String cointainerName = "playerRecords";

        plyr1 = findViewById(R.id.playerone);
        plyr2 = findViewById(R.id.playerone);
        Age1 = (EditText) findViewById(R.id.aage);
        Age2 = (EditText) findViewById(R.id.aage);
     //   MobileNo1 = (EditText) findViewById(R.id.mmobile);
     //   MobileNo2 = (EditText) findViewById(R.id.mmobile);

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
*/

    public class importingDatabaseItem extends AsyncTask<String, String, String> {

        String z="";
        Boolean isSuccess=false;
        String name1="";

        protected void onPreExecute(){

        }
        @Override
        protected void onPostExecute(String r){

            Toast.makeText(TwoNameActivity.this, r, Toast.LENGTH_LONG).show();
            if(isSuccess)
            {
                //  userdata=(Textview) findViewById(R.id.userid);
                //   userdata.setText(name1);

                secretCode1=(EditText) findViewById(R.id.secretCode1);
                secretCode1.setText(name1);

                secretCode2=(EditText) findViewById(R.id.secretCode2);
                secretCode2.setText("************************");


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

        //    StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy().Builder().permitAll().build();
        //    StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String ConnectionURL=null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://tictacserver.database.windows.net:1433;DatabaseName=TicTacProject;user=TicTacServerAdmin@tictacserver;password=Shubham2000@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
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

    public void onBackedPressed()
    {

        Intent i=new Intent(TwoNameActivity.this, MainActivity.class);
        startActivity(i);
    }
}