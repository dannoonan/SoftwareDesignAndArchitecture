package com.cs4125.bikerentalapp.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs4125.bikerentalapp.R;
import com.cs4125.bikerentalapp.model.entity.BikeCredential;
import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.viewmodel.BikeViewModel;
import com.cs4125.bikerentalapp.web.ResponseBody;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class AddBikeFragment extends Fragment {

    private EditText bikeTypeField;
    private EditText nodeIdId;
    private Button addBikeButton;
    private NavController navController;

    private BikeViewModel bikeViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_bike, container, false);

        configureUiItems(v);
        configureViewModel();
        return v;
    }

    private void configureUiItems(View view) {
        bindUiItems(view);
        Navigation.setViewNavController(view, new NavController(getContext()));
        navController = NavHostFragment.findNavController(this);
        addBikeButton.setOnClickListener(view1 -> addBike());
    }

    private void bindUiItems(View view){
        bikeTypeField = view.findViewById(R.id.bikeTypeId);
        nodeIdId = view.findViewById(R.id.nodeId);
        addBikeButton = view.findViewById(R.id.createBikeButton);
    }

    private void configureViewModel(){
        bikeViewModel = ViewModelProviders.of(this).get(BikeViewModel.class);
        bikeViewModel.init(ServiceLocator.get(BikeRepository.class));
    }

    private void addBike(){
    BikeCredential bikeCredential = new BikeCredential
            .Builder()
            .setBikeType(bikeTypeField.getText().toString())
            .setNodeId(nodeIdId.getText().toString())
            .build();

    LiveData<ResponseBody> liveResponse = bikeViewModel.addBike(bikeCredential);

    //OBSERVER PATTERN
        liveResponse.observe(this, this::observeResponse);
    }

    private void observeResponse(@Nullable ResponseBody response){
        if(response != null) {
            if (response.getResponseCode() == 200) {
                showToast("Bike Addition Successful");

                navController.navigate(R.id.action_addBikeFragment_to_menuFragment);
            } else {
                showToast("Bike Addition Failed");
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
