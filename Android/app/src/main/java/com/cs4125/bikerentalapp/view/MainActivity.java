package com.cs4125.bikerentalapp.view;

import android.os.Bundle;
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


}
