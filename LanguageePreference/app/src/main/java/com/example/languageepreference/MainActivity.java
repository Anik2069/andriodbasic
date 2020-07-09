package com.example.languageepreference;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView t1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.english){
            setLang("English");
        }else{
            setLang("Spanish");
        }
        return true;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 =(TextView) findViewById(R.id.tom);

         sharedPreferences = this.getSharedPreferences("com.example.languageepreference", Context.MODE_PRIVATE);

        String langeue=sharedPreferences.getString("lang","");
        if(langeue==""){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a Language")
                    .setMessage("Which Language would you like")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLang("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLang("Spanish");
                        }
                    }).show();
        }else{
           t1.setText(langeue);
        }

    }


    public void setLang(String lang){
        sharedPreferences.edit().putString("lang",lang).apply();


         t1 =(TextView) findViewById(R.id.tom);
        t1.setText(lang);
    }
}
