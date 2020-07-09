package com.example.mutipleactivitydemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        Toast.makeText(this,intent.getStringExtra("name"),Toast.LENGTH_LONG).show();
    }

    public void toback(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
       // intent.putExtra("name",friends.get(position));
        startActivity(intent);
    }
}
