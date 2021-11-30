package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.os.Handler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {


    boolean easy = true;
    boolean medium;
    boolean hard;
    boolean impossible;

    boolean wins;
    boolean returns;

    boolean player1ax;
    Random r = new Random();

    int flag = 0, ax = 10, zero = 1, sensorflag = 0, win = 0, i, game = 1, prevrow, prevcol;
    int summ = 0, ctrflag = 0, night = 0, resetchecker = 1, currentgamedonechecker = 0;
    int score1 = 0, score2 = 0, drawchecker = 0;
    static int[][] tracker = new int[3][3];
    int[] sum = new int[8];
    static int[][] buttonpressed = new int[3][3];
    boolean selectedsingleplayer;
    private int savedValue;
    CharSequence player1 = "Player 1";
    CharSequence player2 = "Player 2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        final ImageButton settingButton = findViewById(R.id.setting);

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent i = new Intent(GameActivity.this,settingActivity.class);
                startActivity(i);
            }
        });


        try {
            CharSequence[] players = getIntent().getCharSequenceArrayExtra("playersNames");

            player1 = players[0];
            player2 = players[1];

            TextView textView = findViewById(R.id.NameText);
            textView.setText(player1);
        }
        catch(Exception e){

        }

        player1ax = getIntent().getBooleanExtra("player1ax", true);
        selectedsingleplayer = getIntent().getBooleanExtra("selectedPlayer", true);
        if (player1ax) {
            ax = 1;
            zero = 10;
        }
        TextView textName = findViewById(R.id.NameText);
        TextView textName2 = findViewById(R.id.NameText2);


        textName.setText(player1);

        if (!selectedsingleplayer){
            textName2.setText(player2);
            Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();}


    }


    public void kzz(View view) {


        if (win == 0 && buttonpressed[0][0] == 0) {
            if (flag % 2 == 0)
                tracker[0][0] = ax;
            else
                tracker[0][0] = zero;

            printBoard();
            winchecker();
            cpuplay();
            flag++;
            buttonpressed[0][0]++;
        }
    }


    public void kzo(View view) {

        if (win == 0 && buttonpressed[0][1] == 0) {
            if (flag % 2 == 0) tracker[0][1] = ax;
            else tracker[0][1] = zero;

            printBoard();
            winchecker();
            cpuplay();
            buttonpressed[0][1]++;
            flag++;
        }
    }

    public void kzt(View view) {
        if (win == 0 && buttonpressed[0][2] == 0) {
            if (flag % 2 == 0) tracker[0][2] = ax;
            else tracker[0][2] = zero;

            printBoard();
            winchecker();
            cpuplay();
            buttonpressed[0][2]++;
            flag++;
        }
    }

    public void koz(View v) {
        if (win == 0 && buttonpressed[1][0] == 0) {
            if (flag % 2 == 0) tracker[1][0] = ax;
            else tracker[1][0] = zero;

            printBoard();
            winchecker();
            cpuplay();

            ++buttonpressed[1][0];
            flag++;
        }
    }

    public void koo(View v) {
        if (win == 0 && buttonpressed[1][1] == 0) {
            if (flag % 2 == 0) tracker[1][1] = ax;
            else tracker[1][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][1];
            flag++;
        }
    }

    public void kot(View v) {
        if (win == 0 && buttonpressed[1][2] == 0) {
            if (flag % 2 == 0) tracker[1][2] = ax;
            else tracker[1][2] = zero;

            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][2];
            flag++;
        }
    }

    public void ktz(View v) {
        if (win == 0 && buttonpressed[2][0] == 0) {
            if (flag % 2 == 0) tracker[2][0] = ax;
            else tracker[2][0] = zero;

            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][0];
            flag++;
        }
    }

    public void kto(View v) {
        if (win == 0 && buttonpressed[2][1] == 0) {
            if (flag % 2 == 0) tracker[2][1] = ax;
            else tracker[2][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][1];
            flag++;
        }
    }

    public void ktt(View v) {
        if (win == 0 && buttonpressed[2][2] == 0) {
            if (flag % 2 == 0) tracker[2][2] = ax;
            else tracker[2][2] = zero;

            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][2];
            flag++;
        }
    }

    public void cpuplay() {
        if ((selectedsingleplayer) && (win == 0)) {



            final Handler handler = new Handler();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {

                            //add code to be executed after a pause
                            if (ifcpuwin()) ;
                            else if (ifopowin()) ;
                            else if (emptycentre()) ;
                            else if (emptycorner()) ;
                            else emptyany();

                            printBoard();
                            winchecker();

                            flag++;
                        }
                    });
                }
            }, 700);

            return;
        }
    }

    public boolean ifcpuwin() {
        if (!easy) {
            for (i = 0; i < 8; i++) {
                if (sum[i] == 2 * zero) {
                    if (i == 0) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[0][x] == 0)
                                tracker[0][x] = zero;
                    }

                    if (i == 1) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[1][x] == 0)
                                tracker[1][x] = zero;
                    }
                    if (i == 2) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[2][x] == 0)
                                tracker[2][x] = zero;
                    }

                    if (i == 3) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[x][0] == 0)
                                tracker[x][0] = zero;
                    }

                    if (i == 4) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][1] == 0)
                                tracker[x][1] = zero;
                    }

                    if (i == 5) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][2] == 0)
                                tracker[x][2] = zero;
                    }
                    if (i == 6) {

                        for (int y = 0; y < 3; y++)
                            for (int x = 0; x < 3; x++)
                                if (x == y)
                                    if (tracker[x][y] == 0)
                                        tracker[x][y] = zero;
                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0)
                            tracker[0][2] = zero;
                        else if (tracker[1][1] == 0)
                            tracker[1][1] = zero;
                        else tracker[2][0] = zero;

                    }
                    return true;
                }
            }
        }
        return false;
    }


    public boolean ifopowin() {
        if ((!easy) || (!medium)) {

            for (i = 0; i < 8; i++) {
                if (sum[i] == 2 * ax) {
                    if (i == 0) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[0][x] == 0) {
                                tracker[0][x] = zero;
                                buttonpressed[0][x]++;
                            }
                    }

                    if (i == 1) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[1][x] == 0) {
                                tracker[1][x] = zero;
                                buttonpressed[1][x]++;
                            }
                    }
                    if (i == 2) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[2][x] == 0) {
                                tracker[2][x] = zero;
                                buttonpressed[2][x]++;
                            }
                    }

                    if (i == 3) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[x][0] == 0) {
                                tracker[x][0] = zero;
                                buttonpressed[x][0]++;
                            }
                    }

                    if (i == 4) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][1] == 0) {
                                tracker[x][1] = zero;
                                buttonpressed[x][1]++;
                            }
                    }

                    if (i == 5) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][2] == 0) {
                                tracker[x][2] = zero;
                                buttonpressed[x][2]++;
                            }
                    }
                    if (i == 6) {

                        for (int y = 0; y < 3; y++)
                            for (int x = 0; x < 3; x++)
                                if (x == y)
                                    if (tracker[x][y] == 0) {
                                        tracker[x][y] = zero;
                                        buttonpressed[x][y]++;
                                    }


                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0) {
                            tracker[0][2] = zero;
                            buttonpressed[0][2]++;
                        } else if (tracker[1][1] == 0) {
                            tracker[1][1] = zero;
                            buttonpressed[1][1]++;
                        } else {
                            tracker[2][0] = zero;
                            buttonpressed[2][0]++;
                        }


                    }
                    return true;
                }
            }

        }
        return false;
    }

    public boolean emptycentre() {
        if (impossible || hard) {
            if (tracker[1][1] == 0) {
                tracker[1][1] = zero;
                buttonpressed[1][1]++;
                return true;
            }
        }
        return false;
    }

    public boolean emptycorner() {


        if (hard || impossible)
            if (((tracker[0][0] + tracker[2][2]) == 2 * ax) || ((tracker[0][2] + tracker[2][0]) == 2 * ax)) {
                for (int k = 0; k < 3; k++)
                    for (int j = 0; j < 3; j++)
                        if ((k + j) % 2 == 1) {
                            if (tracker[k][j] == 0)
                                tracker[k][j] = zero;
                            buttonpressed[k][j]++;
                            return true;
                        }
            }


        if (impossible)
            if (sum[6] == zero || sum[7] == zero) {
                if (sum[6] == zero) {
                    if ((sum[0] + sum[3]) > (sum[2] + sum[5])) {
                        tracker[0][0] = zero;
                        buttonpressed[0][0]++;
                    } else {
                        tracker[2][2] = zero;
                        buttonpressed[2][2]++;
                    }
                    return true;
                }

                if (sum[7] == zero) {
                    if ((sum[0] + sum[5]) > (sum[3] + sum[2])) {
                        tracker[0][2] = zero;
                        buttonpressed[0][2]++;
                    } else {
                        tracker[2][0] = zero;
                        buttonpressed[2][0]++;
                    }
                    return true;
                }

            }


        for (int i = 0; i < 3; i++) {
            if (tracker[0][i] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero;
                    buttonpressed[0][0]++;
                    return true;
                }
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero;
                    buttonpressed[0][2]++;
                    return true;
                }
            }
        }

        for (int i = 0; i < 3; i++) {

            if (tracker[2][i] == ax) {
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero;
                    buttonpressed[2][0]++;
                    return true;
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero;
                    buttonpressed[2][2]++;
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tracker[i][0] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero;
                    buttonpressed[0][0]++;
                    return true;
                }
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero;
                    buttonpressed[2][0]++;
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tracker[i][2] == ax) {
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero;
                    buttonpressed[0][2]++;
                    return true;
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero;
                    buttonpressed[2][2]++;
                    return true;
                }
            }
        }
        return false;

    }

    public void emptyany() {

        if (ctrflag == 0)
            while (true) {
                int x = rand();
                int y = rand();

                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;

                }
            }

        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;
                }


    }

    public int rand() {
        return r.nextInt(3);
    }

    public void printBoard() {
        ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;

        q1 = (ImageView) findViewById(R.id.tzz);
        q2 = (ImageView) findViewById(R.id.tzo);
        q3 = (ImageView) findViewById(R.id.tzt);
        q4 = (ImageView) findViewById(R.id.toz);
        q5 = (ImageView) findViewById(R.id.too);
        q6 = (ImageView) findViewById(R.id.tot);
        q7 = (ImageView) findViewById(R.id.ttz);
        q8 = (ImageView) findViewById(R.id.tto);
        q9 = (ImageView) findViewById(R.id.ttt);


        if (tracker[0][0] == 1) q1.setImageResource(R.drawable.xscene);
        if (tracker[0][0] == 10) q1.setImageResource(R.drawable.oscene);


        if (tracker[0][1] == 1) q2.setImageResource(R.drawable.xscene);
        if (tracker[0][1] == 10) q2.setImageResource(R.drawable.oscene);


        if (tracker[0][2] == 1) q3.setImageResource(R.drawable.xscene);
        if (tracker[0][2] == 10) q3.setImageResource(R.drawable.oscene);


        if (tracker[1][0] == 1) q4.setImageResource(R.drawable.xscene);
        if (tracker[1][0] == 10) q4.setImageResource(R.drawable.oscene);

        if (tracker[1][1] == 1) q5.setImageResource(R.drawable.xscene);
        if (tracker[1][1] == 10) q5.setImageResource(R.drawable.oscene);


        if (tracker[1][2] == 1) q6.setImageResource(R.drawable.xscene);
        if (tracker[1][2] == 10) q6.setImageResource(R.drawable.oscene);

        if (tracker[2][0] == 1) q7.setImageResource(R.drawable.xscene);
        if (tracker[2][0] == 10) q7.setImageResource(R.drawable.oscene);


        if (tracker[2][1] == 1) q8.setImageResource(R.drawable.xscene);
        if (tracker[2][1] == 10) q8.setImageResource(R.drawable.oscene);

        if (tracker[2][2] == 1) q9.setImageResource(R.drawable.xscene);
        if (tracker[2][2] == 10) q9.setImageResource(R.drawable.oscene);

        resetchecker++;
    }

        public void winchecker() {
            ctrflag++;
            sum[0] = tracker[0][0] + tracker[0][1] + tracker[0][2];
            sum[1] = tracker[1][0] + tracker[1][1] + tracker[1][2];
            sum[2] = tracker[2][0] + tracker[2][1] + tracker[2][2];
            sum[3] = tracker[0][0] + tracker[1][0] + tracker[2][0];
            sum[4] = tracker[0][1] + tracker[1][1] + tracker[2][1];
            sum[5] = tracker[0][2] + tracker[1][2] + tracker[2][2];
            sum[6] = tracker[0][0] + tracker[1][1] + tracker[2][2];
            sum[7] = tracker[0][2] + tracker[1][1] + tracker[2][0];
            final boolean[] wins = new boolean[1];
            final boolean[] returns = new boolean[1];

            currentgamedonechecker++;
            resetchecker++;


            for (int i = 0; i < 8; i++)
                if (sum[i] == 3 || sum[i] == 30) {

                    win++;
                    if ((sum[i] == 3) && (ax == 1)) {
                        score1++;
                        final TextView q1 = (TextView) findViewById(R.id.p1score);
                        wins[0] = true;
                        returns[0] = false;


                        final boolean finalReturns = returns[0];


                        q1.setText("" + score1);

                        // showDialog("" + player1 + " won!", "" + score1, "" + player2, "" + score2);
                        //   Toast.makeText(getApplicationContext(),player1 + " won!", Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                playmore();
                            }
                        }, 500);

                    }
                    if ((sum[i] == 3) && (zero == 1)) {
                        score2++;
                        final TextView q1 = (TextView) findViewById(R.id.p2score);
                        wins[0] = true;
                        returns[0] = false;


                        final boolean finalReturns = returns[0];

                        q1.setText("" + score2);
                        // showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);
                        //  Toast.makeText(getApplicationContext(),"AI won!", Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                playmore();
                            }
                        }, 300);

                    }
                    if ((sum[i] == 30) && (ax == 10)) {


                        score1++;
                        final   TextView q1 = (TextView) findViewById(R.id.p1score);
                        wins[0] = true;
                        returns[0] = false;


                        final boolean finalReturns = returns[0];


                        q1.setText("" + score1);


                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                playmore();
                            }
                        }, 500);

                    }
                    if ((sum[i] == 30) && (zero == 10)) {


                        score2++;
                        final TextView q1 = (TextView) findViewById(R.id.p2score);
                        wins[0] = true;
                        returns[0] = false;


                        final boolean finalReturns = returns[0];


                        q1.setText("" + score2);
                        // to asi netreba
                        //   Toast.makeText(getApplicationContext(), " AI won!", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                playmore();
                            }
                        }, 500);


                        //  showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);

                    }

                }

            if ((ctrflag == 9) && (win == 0)) {
                //  showDialog("This is a draw !", "" + score1, "" + "AI", "" + score2);


                Toast.makeText(getApplicationContext(),"DRAW!",Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playmore();
                    }
                }, 900);
                drawchecker++;
            }


        }  //end winchecker()

    private void playmore() {


        int max = 4;
        int min = 1;

        if (savedValue == 0 || savedValue == 5) {
            int random = new Random().nextInt(max - min + 1) + min;

            if (random == 1){
                easy = true;
                medium = false;
                hard = false;
                impossible = false;

            }
            if (random == 2){
                easy = false;
                medium = true;
                hard = false;
                impossible = false;

            }
            if (random == 3){
                easy = false;
                medium = false;
                hard = true;
                impossible = false;

            }
            if (random == 4){
                easy = false;
                medium = false;
                hard = false;
                impossible = true;
            }
        }
        if (savedValue == 1){

            easy = true;
            medium = false;
            hard = false;
            impossible = false;        }
        if (savedValue == 2){

            easy = false;
            medium = true;
            hard = false;
            impossible = false;        }
        if (savedValue == 3){

            easy = false;
            medium = false;
            hard = true;
            impossible = false;        }
        if (savedValue == 4){

            easy = false;
            medium = false;
            hard = false;
            impossible = true;        }



        if ((drawchecker > 0) || (win > 0)) {
            game++;
            //    TextView qq = (TextView) findViewById(R.id.gamenumber);
            //  qq.setText("" + game);

            for (int i = 0; i < 8; i++)
                sum[i] = 0;

            drawchecker = 0;


            ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;
            q1 = (ImageView) findViewById(R.id.tzz);
            q2 = (ImageView) findViewById(R.id.tzo);
            q3 = (ImageView) findViewById(R.id.tzt);
            q4 = (ImageView) findViewById(R.id.toz);
            q5 = (ImageView) findViewById(R.id.too);
            q6 = (ImageView) findViewById(R.id.tot);
            q7 = (ImageView) findViewById(R.id.ttz);
            q8 = (ImageView) findViewById(R.id.tto);
            q9 = (ImageView) findViewById(R.id.ttt);
            q1.setImageDrawable(null);
            q2.setImageDrawable(null);
            q3.setImageDrawable(null);
            q4.setImageDrawable(null);
            q5.setImageDrawable(null);
            q6.setImageDrawable(null);
            q7.setImageDrawable(null);
            q8.setImageDrawable(null);
            q9.setImageDrawable(null);

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    buttonpressed[i][j] = 0;

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tracker[i][j] = 0;

//ToTo som ja dal prec

            if (!selectedsingleplayer) {
                if ((game + 1) % 2 == 0) {
                    Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "" + player2 + "\'s turn", Toast.LENGTH_SHORT).show();
            }

            win = 0;
            summ = 0;
            ctrflag = 0;
            flag = (game + 1) % 2;
            currentgamedonechecker = 0;

            if (selectedsingleplayer && (game % 2 == 0))
                cpuplay();
        }
    }


    public void doreset() {

        //  TextView qq = (TextView) findViewById(R.id.gamenumber);
        // qq.setText("" + 1);


        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                tracker[i][j] = 0;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttonpressed[i][j] = 0;

        ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;

        q1 = (ImageView) findViewById(R.id.tzz);
        q2 = (ImageView) findViewById(R.id.tzo);
        q3 = (ImageView) findViewById(R.id.tzt);
        q4 = (ImageView) findViewById(R.id.toz);
        q5 = (ImageView) findViewById(R.id.too);
        q6 = (ImageView) findViewById(R.id.tot);
        q7 = (ImageView) findViewById(R.id.ttz);
        q8 = (ImageView) findViewById(R.id.tto);
        q9 = (ImageView) findViewById(R.id.ttt);
        q1.setImageDrawable(null);
        q2.setImageDrawable(null);
        q3.setImageDrawable(null);
        q4.setImageDrawable(null);
        q5.setImageDrawable(null);
        q6.setImageDrawable(null);
        q7.setImageDrawable(null);
        q8.setImageDrawable(null);
        q9.setImageDrawable(null);


        win = 0;
        drawchecker = 0;
        summ = 0;
        resetchecker = 0;
        ctrflag = 0;
        score1 = 0;
        score2 = 0;
        game = 1;
        flag = 0;
        currentgamedonechecker = 0;
        TextView qqq = (TextView) findViewById(R.id.p1score);
        qqq.setText("" + score1);
        TextView qqqq = (TextView) findViewById(R.id.p2score);
        qqqq.setText("" + score2);

        Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();


    }

/*
    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.

                makeSceneTransitionAnimation(this, view, "transition");

        int revealX = (int) (view.getX() + view.getWidth() / 2);

        int revealY = (int) (view.getY() + view.getHeight() / 2);
        Intent intent =
                new Intent(this, settingActivity.class);
        intent.putExtra(settingActivity.
                EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(settingActivity.
                EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.
                startActivity(this, intent, options.toBundle());
    }


 */

    @Override
    public void onBackPressed()
    {
        Intent intentBack=new Intent(GameActivity.this, MainActivity.class);
        startActivity(intentBack);
    }
}
