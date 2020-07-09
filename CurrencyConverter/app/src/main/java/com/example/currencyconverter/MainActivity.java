package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toDollar(View view){
        EditText editText= (EditText) findViewById(R.id.editText);
        double dolar= Double.parseDouble(editText.getText().toString())/80;
        Toast.makeText(MainActivity.this,"BDT is converted to Dolar and amount is:"+dolar+" Dolar",Toast.LENGTH_LONG).show();
    }
    public void toTaka(View view){
        EditText editText= (EditText) findViewById(R.id.editText);
        double dolar= Double.parseDouble(editText.getText().toString())*80;
        Toast.makeText(MainActivity.this,"Dolar is converted to Taka and amount is:"+dolar+" BDT",Toast.LENGTH_LONG).show();
    }
}
