package com.example.newsread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    SQLiteDatabase articledb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView) findViewById(R.id.list);
        arrayAdapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("content",content.get(position));

                startActivity(intent);
            }
        });

        // DB Create
        articledb= this.openOrCreateDatabase("Articles",MODE_PRIVATE,null);

        articledb.execSQL("CREATE TABLE IF NOT EXISTS articles (ID INTERGER PRIMARY KEY,ARTICLEID INTEGER,TITLE VARCHAR,CONTENT VARCHAR)");
        update();
        //API CAlls
        DownloadTask task=new DownloadTask();
        try {
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void update(){
        Cursor c =articledb.rawQuery("Select * from articles",null);
        int contextIndex = c.getColumnIndex("CONTENT");
        int titleindex = c.getColumnIndex("TITLE");
        if(c.moveToFirst()){
            titles.clear();
            content.clear();
            do{
                titles.add(c.getString(titleindex));
                content.add(c.getString(contextIndex));

            }while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();

        }
    }

    public class DownloadTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String result ="";
            URL url;
            HttpURLConnection urlConnection =null;

            try {
                url= new URL(strings[0]);
                urlConnection =(HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader =new InputStreamReader(in);
                int data =reader.read();

                while (data!= -1){
                    char curremt =(char) data;
                    result +=curremt;
                    data=reader.read();
                }
                Log.i("URl",result);
                JSONArray jsonArray=new JSONArray(result);
                int numberitem=20;
                if(jsonArray.length()<20){
                    numberitem=jsonArray.length();
                }

                articledb.execSQL("DELETE from articles");

                for (int i=0;i<numberitem;i++){
                    String articleid=jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/"+articleid+".json?print=pretty");
                    urlConnection =(HttpURLConnection) url.openConnection();
                    in =urlConnection.getInputStream();
                    reader=new InputStreamReader(in);
                    data=reader.read();
                    String articleinfo="";
                    while (data!=-1){
                        char current =(char)data;
                        articleinfo+=current;
                        data =reader.read();
                    }
                    //Log.i("Arti",articleinfo);

                    JSONObject jsonObject=new JSONObject(articleinfo);
                    if(!jsonObject.isNull("title")&& !jsonObject.isNull("url")){
                        String artitle =jsonObject.getString("title");
                        String arurl=jsonObject.getString("url");
                        url = new URL(arurl);
                        urlConnection =(HttpURLConnection) url.openConnection();
                        in =urlConnection.getInputStream();
                        reader=new InputStreamReader(in);
                        data=reader.read();
                        String articConetent="";
                        while (data!=-1){
                            char current =(char)data;
                            articleinfo+=current;
                            data =reader.read();
                        }

                        String sql = "INSERT INTO articles (ARTICLEID,TITLE,CONTENT) VALUES (?,?,?)";
                        SQLiteStatement statement = articledb.compileStatement(sql);
                        statement.bindString(1,articleid);
                        statement.bindString(2,artitle);
                        statement.bindString(3,articConetent);
                        statement.execute();

                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            update();
        }
    }
}
