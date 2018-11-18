package com.macmanus.jamie.bikerentalapp.view;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.macmanus.jamie.bikerentalapp.R;
import com.macmanus.jamie.bikerentalapp.repository.BikeRepository;
import com.macmanus.jamie.bikerentalapp.sl.ServiceLocator;
import com.macmanus.jamie.bikerentalapp.viewmodel.IRentViewModel;
import com.macmanus.jamie.bikerentalapp.viewmodel.RentViewModel;
import com.macmanus.jamie.bikerentalapp.web.ResponseBody;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmRentFragment extends Fragment {


    private String input = "";
    private int RentOrReturn;
    private int bikeId;
    Button confirm;
    private IRentViewModel rentViewModel;
    private NavController navController;
    public ConfirmRentFragment() {
    }

    @SuppressLint("ValidFragment")
    public ConfirmRentFragment(int ROrR, String in) {
        RentOrReturn = ROrR;
       input = in;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_confirm_rent, container, false);

        configureUI(v);
        configureUiItems(v);
        configureServiceLocator();
        configureViewModel();

        return v;
    }

    private void configureUI(View v){
        String[] data = input.split(",");
        TextView idText;
        TextView typeText;
        bikeId = Integer.parseInt(data[0]);
        confirm = v.findViewById(R.id.confirmBtn);
        idText = v.findViewById(R.id.idText);
        typeText = v.findViewById(R.id.typeText);
        idText.setText("Bike ID: "+data[0]);
        typeText.setText("Bike Type: "+data[1]);
    }

    private void configureServiceLocator(){
        ServiceLocator.bindCustomServiceImplementation(IRentViewModel.class, RentViewModel.class);
    }

    private void configureViewModel(){
        rentViewModel = ServiceLocator.get(IRentViewModel.class);
        rentViewModel.init(ServiceLocator.get(BikeRepository.class));
    }


    private void configureUiItems(View view) {
        Navigation.setViewNavController(view, new NavController(getContext()));
        navController = NavHostFragment.findNavController(this);

        confirm.setOnClickListener(view1 -> setBikeStatus());
    }


    private void setBikeStatus(){
        String username = "roryegan";

        LiveData<ResponseBody> liveResponse;
        if(RentOrReturn == 1){
            liveResponse = rentViewModel
                    .rentBike(username, bikeId, 100);
        }
        else{
            liveResponse = null;
        }
            liveResponse.observe(this, this::observeResponse);

    }

    private void observeResponse(@Nullable ResponseBody response){
        if(response != null) {
            if (response.getResponseCode() == 200) {
                showToast("Rent/Return Successful");


            } else {
                showToast("Rent/Return Failed");
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
