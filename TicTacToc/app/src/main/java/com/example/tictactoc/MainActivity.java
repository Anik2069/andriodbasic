package com.example.tictactoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer = 0;
    //0 red 1 green
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    boolean gameactive = true;

    int[][] win = {
            {0, 2, 1}, {4, 3, 5}, {6, 7, 8}, {0, 4, 6}, {2, 3, 7}, {1, 5, 8}, {0, 3, 8}, {1, 3, 6}
    };

    public void playaain(View view) {
        gameactive = true;
        LinearLayout ly = (LinearLayout) findViewById(R.id.playagain);
        ly.setVisibility(view.INVISIBLE);
        activeplayer = 0;
        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }
        androidx.gridlayout.widget.GridLayout g1 = findViewById(R.id.tomcat);
        System.out.println(g1.getChildCount());
        for (int i = 0; i < g1.getChildCount(); i++) {

            ((ImageView) g1.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1000f);
        //Get tag from ui
        int tapCounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tapCounter] == 2 && gameactive) {
            gamestate[tapCounter] = activeplayer;
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.red);
                activeplayer = 1;

            } else {
                counter.setImageResource(R.drawable.green);
                activeplayer = 0;

            }
        }


        counter.animate().translationYBy(1000f).rotation(360).setDuration(2000);
        for (int[] s : win) {
            if (gamestate[s[0]] == gamestate[s[1]]
                    && gamestate[s[1]] == gamestate[s[2]] &&
                    gamestate[s[0]] != 2) {
                String winner = "Green";
                if (gamestate[s[0]] == 0) {
                    winner = "Red";
                }
                gameactive = false;
                TextView t1 = (TextView) findViewById(R.id.textView3);
                t1.setText(winner + " Won the Game");
                LinearLayout ly = (LinearLayout) findViewById(R.id.playagain);
                ly.setVisibility(view.VISIBLE);
            }

        }
    }


}
