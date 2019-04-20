package com.cs4125.bikerentalapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs4125.bikerentalapp.R;

public class AddBikeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_bike, container, false);

        configureUiItems(v);
        return v;
    }

    private void configureUiItems(View v){
        TextView textArea;
        textArea = v.findViewById(R.id.idText);
        textArea.setText("Test");
    }
}
