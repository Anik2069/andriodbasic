package com.example.downloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imga;

    public void downloadimage(View view) {
//https://i.pinimg.com/originals/31/34/28/3134281576b5d424760e215165b27022.jpg
        ImageDownload img = new ImageDownload();
        Bitmap myimage;
        try {
            myimage = img.execute("https://i.pinimg.com/originals/31/34/28/3134281576b5d424760e215165b27022.jpg").get();
            imga.setImageBitmap(myimage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imga = (ImageView) findViewById(R.id.imageView);

    }

    public class ImageDownload extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inp = connection.getInputStream();
                Bitmap mybit = BitmapFactory.decodeStream(inp);
                return mybit;
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
