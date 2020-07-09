package com.example.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView i = (ImageView) findViewById(R.id.imageView2);
        i.setTranslationX(-1000f);
    }

    public void fade(View view) {
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.animate().rotation(1800f).setDuration(2000);
      //  i.animate().translationXBy(1000f).setDuration(2000);
        //i.animate().translationYBy(1000f).setDuration(2000);
        //i.animate().translationYBy(-1000f).setDuration(2000);
        //i.animate().translationXBy(-1000f).setDuration(2000);


       /* i.animate().alpha(0f).setDuration(2000);

        ImageView i2 =(ImageView) findViewById(R.id.imageView2);
        i2.animate().alpha(1f).setDuration(2000);
*/
    }

    public void fade2(View view) {
        ImageView i2 = (ImageView) findViewById(R.id.imageView2);
        i2.animate().alpha(0f).setDuration(2000);

        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.animate().alpha(1f).setDuration(2000);


    }
}
