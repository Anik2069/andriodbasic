package com.example.memorableplace;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    public void centerMapLocation(Location location, String title) {
        LatLng userlaction = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.clear();

        if (title != "Your Location") {
            mMap.addMarker(new MarkerOptions().position(userlaction).title(title));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlaction, 10));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centerMapLocation(lastlocation, "Your Location");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(this);
        Intent intent = getIntent();
        // Toast.makeText(this,intent.getStringExtra("PlaceNumber"),Toast.LENGTH_LONG).show();
        if (intent.getIntExtra("PlaceNumber", 0) == 0) {
            //zoom
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    centerMapLocation(location, "Your Location");
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

            } else {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    Location lastlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    centerMapLocation(lastlocation, "Your Location");
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }
        }else{
            Location placeLocation = new Location(LocationManager.GPS_PROVIDER);
            placeLocation.setLatitude(MainActivity.locations.get(intent.getIntExtra("PlaceNumber",0)).latitude);
            placeLocation.setLongitude(MainActivity.locations.get(intent.getIntExtra("PlaceNumber",0)).longitude);

            centerMapLocation(placeLocation,MainActivity.places.get(intent.getIntExtra("PlaceNumber",0)));
           // mMap.addMarker(new MarkerOptions().position(MainActivity.locations.get(intent.getIntExtra("PlaceNumber",0))).title(MainActivity.places.get(intent.getIntExtra("PlaceNumber",0))));

        }

    }


    @Override
    public void onMapClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "";
        try {
            List<Address> listadd = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (listadd != null && listadd.size() > 0) {
                if (listadd.get(0).getThoroughfare() != null) {
                    if (listadd.get(0).getSubThoroughfare() != null) {
                        address += listadd.get(0).getSubThoroughfare();
                    }
                    address += listadd.get(0).getThoroughfare();
                }
            }
        } catch (Exception e) {

        }
        if (address == "") {
            SimpleDateFormat sdf = new SimpleDateFormat("mm:HH yyyyMMdd");
            address = sdf.format(new Date());
        }
        mMap.addMarker(new MarkerOptions().position(latLng).title("Your New Place"));

        MainActivity.places.add(address);
        MainActivity.locations.add(latLng);
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences =  this.getSharedPreferences("com.example.memorableplace",Context.MODE_PRIVATE);

        try {

            ArrayList<String> latitudes = new ArrayList<>();
            ArrayList<String> longitudes = new ArrayList<>();



            for(LatLng coordinates: MainActivity.locations){
                latitudes.add(Double.toString(coordinates.latitude));
                longitudes.add(Double.toString(coordinates.longitude));

            }

            sharedPreferences.edit().putString("places",ObjectSerializer.serialize(MainActivity.places)).apply();
            sharedPreferences.edit().putString("latitudes",ObjectSerializer.serialize(latitudes)).apply();
            sharedPreferences.edit().putString("longitudes",ObjectSerializer.serialize(longitudes)).apply();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,"Location Saved", Toast.LENGTH_LONG).show();
    }

}

