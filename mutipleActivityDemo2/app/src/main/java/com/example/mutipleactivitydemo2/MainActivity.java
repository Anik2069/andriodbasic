package com.example.mutipleactivitydemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView =(ListView) findViewById(R.id.listview);
        final ArrayList<String> friends= new ArrayList<String>();
        friends.add("Honey");
        friends.add("Rinky");
        friends.add("Mima");
        friends.add("Mukti");
        friends.add("Kaynat");
        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,friends);
listView.setAdapter(arrayAdapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("name",friends.get(position));
        startActivity(intent);
    }
});
    }
}
