package com.example.multiactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tosecond(View view){
        Intent intent= new Intent(getApplicationContext(),second_Activity.class);
        intent.putExtra("User","Anik");

        startActivity(intent);
    }
}
