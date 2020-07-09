package com.example.myapps2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickMe(View view) {
        // Toast.makeText(MainActivity.this,"Hi There",Toast.LENGTH_LONG).show();
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        Toast.makeText(MainActivity.this, "Hi there," + editText2.getText().toString(), Toast.LENGTH_LONG).show();

    }

    public void newImage(View view) {
        // Toast.makeText(MainActivity.this,"Hi There",Toast.LENGTH_LONG).show();
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        if (a == 0) {
            imageView3.setImageResource(R.drawable.pic);
            a = 1;
        } else {
            imageView3.setImageResource(R.drawable.anik);
            a = 0;
        }
    }

}
