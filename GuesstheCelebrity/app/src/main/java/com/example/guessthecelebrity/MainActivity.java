package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> celeUrl = new ArrayList<String>();
    ArrayList<String> celeName = new ArrayList<String>();
    int choosecele = 0;
    ImageView imageview;
    int locationanswer = 0;
    String[] answer = new String[4];
    Button b1;
    Button b2;
    Button b3;
    Button b4;

    public class Imagedown extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap mybit = BitmapFactory.decodeStream(in);
                return mybit;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public class downloadtask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String res = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    res += current;
                    data = reader.read();
                }
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = (ImageView) findViewById(R.id.imageView);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        downloadtask d1 = new downloadtask();
        String res = null;
        try {
            res = d1.execute("http://www.posh24.se/kandisar").get();
            //Split the result
            String[] splitRes = res.split("<div class =\"sidebarContainer\">");
            //Patterns
            Pattern p = Pattern.compile("<img src=\"(.*?)\"");
            Matcher m = p.matcher(splitRes[0]);
            while (m.find()) {
                celeUrl.add(m.group(1));
            }
            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitRes[0]);
            while (m.find()) {
                celeName.add(m.group(1));
            }
            newquestion();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void celebritychoose(View view) {
        if (view.getTag()
                .toString().equals(Integer.toString(locationanswer))) {
            Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"wrong! it was"+celeName.get(choosecele),Toast.LENGTH_LONG).show();

        }
        newquestion();
    }
    public void newquestion(){
        Random random = new Random();

        choosecele = random.nextInt(celeUrl.size());//choose korse ekta celebrity sob gula thaoka tar link imager

        Imagedown imagedown = new Imagedown();
        Bitmap my;
        try {
            my = imagedown.execute(celeUrl.get(choosecele)).get();//choose korse ekta celebrity sob gula thaoka tar  image

            imageview.setImageBitmap(my);
            int incorrect;

            locationanswer = random.nextInt(4);
            for (int i = 0; i < 4; i++) {
                if (i == locationanswer) {
                    answer[i] = celeName.get(choosecele);// je celebrity choose korse tar naam
                } else {
                    incorrect = random.nextInt(celeUrl.size());
                    while (incorrect == choosecele) {
                        incorrect = random.nextInt(celeUrl.size());

                    }
                    answer[i] = celeName.get(incorrect);
                }
            }
            b1.setText(answer[0]);
            b2.setText(answer[1]);
            b3.setText(answer[2]);
            b4.setText(answer[3]);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
