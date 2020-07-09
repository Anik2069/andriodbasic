package com.example.multiactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class second_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);
        Intent intent =getIntent();
        Toast.makeText(this,intent.getStringExtra("User"),Toast.LENGTH_LONG).show();
    }
    public void toback(View view){
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
