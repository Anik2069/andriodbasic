package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timeSeekbar;
    TextView timerview;
    Boolean counteractive=false;
    Button contrl;
    CountDownTimer cd;

    public void updatetimerd(int secondsleft) {

        int minitus = (int) secondsleft / 60;
        int seconds = secondsleft - minitus * 60;

        String secondstring = Integer.toString(seconds);
        if (secondstring == "0") {
            secondstring = "00";
        }else if(seconds<=9){
            secondstring ="0"+secondstring;
        }

        timerview.setText(Integer.toString(minitus) + ":" + secondstring);


    }

    public void cntrltimer(View view) {
        if(counteractive==false) {
            counteractive = true;
            timeSeekbar.setEnabled(false);
            contrl.setText("Stop");
        cd=    new CountDownTimer(timeSeekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updatetimerd((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    reset();
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.air);
                    mp.start();
                }
            }.start();
        }else{
            reset();
        }
    }
    public void reset(){
        timerview.setText("0:30");
        timeSeekbar.setProgress(30);
        cd.cancel();
        contrl.setText("Go");
        timeSeekbar.setEnabled(true);
        counteractive=false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contrl =(Button) findViewById(R.id.button);
        timeSeekbar = (SeekBar) findViewById(R.id.seekBar);

        timerview = (TextView) findViewById(R.id.td1);


        timeSeekbar.setMax(600);
        timeSeekbar.setProgress(30);
        timeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetimerd(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
