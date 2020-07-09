package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Button Function.
    public void clickMe(View view){
        //show message in log. log.i used to show message in Logcat in andriod studio.
        //Log.i("Info","Button is pressed!!!!");
        //Part 2 to take data
        EditText editText3=(EditText) findViewById(R.id.editText3);

        EditText wifename=(EditText) findViewById(R.id.wifename);

        Log.i("Name","Your Name is :"+editText3.getText().toString());
        Log.i("WifeName","Your Wife Name is :"+wifename.getText().toString());

    }


}
