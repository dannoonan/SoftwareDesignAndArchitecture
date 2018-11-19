package com.cs4125.bikerentalapp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs4125.bikerentalapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleCollectionFragment extends Fragment {


    public ScheduleCollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_collection, container, false);
    }

}
