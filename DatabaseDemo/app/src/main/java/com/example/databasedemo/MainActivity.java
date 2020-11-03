package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase mydata = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
            mydata.execSQL("CREATE TABLE IF NOT EXISTS STUDENTS (name VARCHAR,age INT(3))");
            mydata.execSQL("insert into STUDENTS (name, age) VALUES ('Anik',24)");

            mydata.execSQL("insert into STUDENTS (name, age) VALUES ('MIMA',24)");

            Cursor c = mydata.rawQuery("SELECT * FROM STUDENTS",null);

            int nameIndex =c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            c.moveToFirst();
            while (c!= null){
                Log.i("name",c.getString(nameIndex));
                Log.i("age",c.getString(ageIndex));

                c.moveToNext();
            }




        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
