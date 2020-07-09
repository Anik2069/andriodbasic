package com.example.showorhideelements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t11 = (TextView) findViewById(R.id.t1);

    }
    public void show(View view){
        t11.setVisibility(View.VISIBLE);
    }
    public void hide(View view){
        t11.setVisibility(View.INVISIBLE);
    }
}
