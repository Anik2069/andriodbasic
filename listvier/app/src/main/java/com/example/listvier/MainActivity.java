package com.example.listvier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView mylist = findViewById(R.id.listvie);
        final ArrayList<String> myfrined = new ArrayList<String>();

        myfrined.add("Shuvo");
        myfrined.add("Ashik");
        myfrined.add("Nuhar husband");
        myfrined.add("Dipu");
        myfrined.add("Rasel");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myfrined);
        mylist.setAdapter(arrayAdapter);
        //Ei upore tuku korle list asbe
        //Click korar jnno pore COde gula
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          @Override
                                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                              Toast.makeText(getApplicationContext(),"Hello "+ myfrined.get(position),Toast.LENGTH_LONG).show();
                                          }
                                      }

        );
    }
}
