package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        n = rand.nextInt(20) + 1;
    }

    public void guessCheck(View view) {
        EditText e1 = (EditText) findViewById(R.id.editText);
        int  value =Integer.parseInt(e1.getText().toString());
        if(n>value){
            Toast.makeText(MainActivity.this,"Higher",Toast.LENGTH_LONG).show();
        }
        else if(n<value){
            Toast.makeText(MainActivity.this,"Lower",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"You Guess that Number",Toast.LENGTH_LONG).show();
            Random rand = new Random();
            n = rand.nextInt(20) + 1;
        }
    }
}
