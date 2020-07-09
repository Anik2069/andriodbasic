package com.example.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;


    public void updateLocatioinfo(Location location) {
        TextView lat = (TextView) findViewById(R.id.textView4);
        TextView lon = (TextView) findViewById(R.id.textView5);
        TextView att = (TextView) findViewById(R.id.textView6);
        TextView alti = (TextView) findViewById(R.id.textView3);
        TextView add = (TextView) findViewById(R.id.textView7);
       // TextView lat = (TextView) findViewById(R.id.textView5);
        lat.setText("Latitude: "+location.getLatitude());
        lon.setText("Longitude: "+location.getLongitude());
        att.setText("Accuracy: "+location.getAccuracy());
        alti.setText("Altitude: "+location.getAltitude());
        String address="Could not find address";
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try{


            List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if(listAddress !=null && listAddress.size() > 0){
                address="Address:\n";
                if(listAddress.get(0).getThoroughfare()!=null){
                    address+=listAddress.get(0).getThoroughfare()+"\n";
                }
                if(listAddress.get(0).getLocality()!=null){
                    address+=listAddress.get(0).getLocality()+"\n";
                }
                if(listAddress.get(0).getPostalCode()!=null){
                    address+=listAddress.get(0).getPostalCode()+",";
                }
                if(listAddress.get(0).getCountryName()!=null){
                    address+=listAddress.get(0).getCountryName()+".";
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        add.setText(address);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startlising();
        }
    }


    public void startlising() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocatioinfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {
            startlising();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    updateLocatioinfo(location);
                }
            }
        }
    }
}
