package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int a = 0, b = 0;
    Button stbtn;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int location;
    int score = 0;
    TextView result;
    TextView point;
    int question = 0;
    TextView timer;
    Button pa;
    ConstraintLayout r1;

    public void start(View view) {
        pa = (Button) findViewById(R.id.pa);
        stbtn.setVisibility(View.INVISIBLE);
        r1.setVisibility(View.VISIBLE);
        playagain(findViewById(R.id.pa));

    }

    public void chooseanswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(location))) {
            score++;
            result.setText("Correct!");
        } else {
            result.setText("InCorrect!");

        }
        question++;
        point.setText(Integer.toString(score) + "/" + Integer.toString(question));
        genquestion();
    }

    public void genquestion() {
        Button b1 = (Button) findViewById(R.id.butto);
        Button b2 = (Button) findViewById(R.id.butt);

        Button b3 = (Button) findViewById(R.id.but);

        Button b4 = (Button) findViewById(R.id.bu);

        TextView sum = (TextView) findViewById(R.id.sum);
        answers.clear();
        Random rand = new Random();
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));
        location = rand.nextInt(4);
        int incoorect;
        for (int i = 0; i < 4; i++) {
            if (i == location) {
                answers.add(a + b);

            } else {
                incoorect = rand.nextInt(41);
                while (incoorect == a + b) {
                    incoorect = rand.nextInt(41);
                }
                answers.add(incoorect);

            }
        }

        b1.setText(Integer.toString(answers.get(0)));
        b2.setText(Integer.toString(answers.get(1)));
        b3.setText(Integer.toString(answers.get(2)));
        b4.setText(Integer.toString(answers.get(3)));

    }

    public void playagain(View view) {
        score = 0;
        question = 0;

        timer.setText("30s");
        point.setText("0/0");
        result.setText("");
        result.setText("");
        pa.setVisibility(View.INVISIBLE);
        genquestion();
        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                result.setText("Final Score: " + Integer.toString(score) + "/" + Integer.toString(question));
                pa.setVisibility(View.VISIBLE);
                GridLayout gridLayout =(GridLayout) findViewById(R.id.gridLayout);
                gridLayout.setVisibility(View.INVISIBLE);
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stbtn = (Button) findViewById(R.id.btn);
        result = (TextView) findViewById(R.id.result);
        point = (TextView) findViewById(R.id.points);
        timer = (TextView) findViewById(R.id.timerview);
        pa = (Button) findViewById(R.id.pa);
        r1 = (ConstraintLayout) findViewById(R.id.rl1);


       // playagain(findViewById(R.id.pa));
    }
}
