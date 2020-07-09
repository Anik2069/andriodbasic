package com.example.shareprefarence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.shareprefarence", Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("username","Anik").apply();
        //String user=sharedPreferences.getString("username","");
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Monica");
        friends.add("Anika");
        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
        }catch (Exception e){

        }

        ArrayList<String> newfriends = new ArrayList<>();

        try {
            newfriends= ( ArrayList<String> )ObjectSerializer.deserialize(sharedPreferences.getString("friends",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
