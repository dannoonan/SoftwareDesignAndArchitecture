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

    Button findBikesButton;
    Button rentBikeButton;
    Button returnBikeButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        configureUiItems(view);
        bindUiItems();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void configureUiItems(View view){
        findBikesButton = view.findViewById(R.id.buttonFindBikes);
        rentBikeButton = view.findViewById(R.id.buttonRentBike);
        returnBikeButton = view.findViewById(R.id.buttonReturnBike);
    }

    private void bindUiItems(){
        findBikesButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_findBikeFragment, null));
        rentBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_rentBikeFragment, null));
        returnBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_returnBikeFragment, null));
    }
}
