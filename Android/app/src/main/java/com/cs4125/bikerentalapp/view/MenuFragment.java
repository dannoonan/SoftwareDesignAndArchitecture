package com.cs4125.bikerentalapp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cs4125.bikerentalapp.R;

import androidx.navigation.Navigation;


public class MenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Button findBikesButton = getView().findViewById(R.id.buttonFindBikes);
        Button rentBikeButton = getView().findViewById(R.id.buttonRentBike);
        Button returnBikeButton = getView().findViewById(R.id.buttonReturnBike);
        Button reportBikeButton = getView().findViewById(R.id.buttonReportBike);

        findBikesButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_findBikeFragment, null));
        rentBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_rentBikeFragment, null));
        returnBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_returnBikeFragment, null));
        reportBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_reportBikeFragment, null));
    }
}
