package com.example.weatherapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText cityname;
    TextView t1;

    public void findweather(View view) {
        //hide keyboard
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(cityname.getWindowToken(), 0);
        try {
            String encoded= URLEncoder.encode( cityname.getText().toString() ,"UTF-8");
            Downloadtask task = new Downloadtask();
            task.execute("http://api.openweathermap.org/data/2.5/weather?q=" +encoded + "&appid=f1840c36c79e37f8e95a1b1fa8f81439");

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Could Not find Weather",Toast.LENGTH_LONG);

        }

    }

    public class Downloadtask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
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
                    result += current;
                    data = reader.read();
                }
                Log.i("error",result);
                return result;
            } catch (Exception e) {
                Log.i("error",e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                String msg = "";
                JSONObject job = new JSONObject(result);
                String weather = job.getString("weather");
                JSONArray arr = new JSONArray(weather);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jpart = arr.getJSONObject(i);
                    String main = "";
                    String des = "";
                    main = jpart.getString("main");
                    des = jpart.getString("description");
                    if (main != "" && des != "") {
                        msg ="Status: "+ main + ",  DesCription:" + des + "\r\n";
                    }

                }
                if (msg != "") {
                    t1.setText(msg);
                } else {
                    Toast.makeText(getApplicationContext(),"Could Not find Weather",Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Could Not find Weather",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityname = (EditText) findViewById(R.id.editText);
        t1 = (TextView) findViewById(R.id.textView2);
    }
}
