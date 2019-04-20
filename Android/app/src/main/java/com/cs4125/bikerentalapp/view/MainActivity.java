package com.cs4125.bikerentalapp.view;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.cs4125.bikerentalapp.R;
import com.cs4125.bikerentalapp.sl.SlConfigurationBuilder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initializeServiceLocator();
    }

    private void initializeServiceLocator(){
        SlConfigurationBuilder.init(this);
        SlConfigurationBuilder.addWebservice();
        SlConfigurationBuilder.addUserRepositories(this);
        SlConfigurationBuilder.addBikeRepository();
    }

    public Location getLocation(){
        Location loc;
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        try{
            loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return loc;
        }catch(SecurityException e){
            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.ACCESS_FINE_LOCATION  },
                    1);
        }

        return null;
    }


}
