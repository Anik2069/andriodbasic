package com.example.downloadapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class downloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();// hold the data
                InputStreamReader reader = new InputStreamReader(in);// read the hold  data
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();//read next data
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadTask d1 = new downloadTask();
        String res = null;
        try {
            res = d1.execute("https://www.ecowebhosting.co.uk").get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException
                e) {
            e.printStackTrace();

        }
        Log.i("Content",res);
    }
}
