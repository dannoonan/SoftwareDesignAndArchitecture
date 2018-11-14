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
import com.macmanus.jamie.bikerentalapp.repository.UserRepository;
import com.macmanus.jamie.bikerentalapp.sl.ServiceLocator;
import com.macmanus.jamie.bikerentalapp.viewmodel.RentViewModel;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmRentFragment extends Fragment {


    private String input = "";
    private int RentOrReturn;
    Button confirm;
    private RentViewModel rentViewModel;
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
        String[] data = input.split(",");
        TextView idText;
        TextView typeText;
        confirm = v.findViewById(R.id.confirmBtn);
        idText = v.findViewById(R.id.idText);
        typeText = v.findViewById(R.id.typeText);
        idText.setText("Bike ID: "+data[0]);
        typeText.setText("Bike Type: "+data[1]);
        configureUiItems(v);
        configureServiceLocator();
        configureViewModel();


        return v;
    }

    private void configureServiceLocator(){
        RentViewModel rentViewModel = new RentViewModel(ServiceLocator.get(UserRepository.class));
        ServiceLocator.addServiceInstance(RentViewModel.class, rentViewModel);
    }

    private void configureViewModel(){
        rentViewModel = ServiceLocator.get(RentViewModel.class);
    }


    private void configureUiItems(View view) {
        Navigation.setViewNavController(view, new NavController(getContext()));
        navController = NavHostFragment.findNavController(this);

        confirm.setOnClickListener(view1 -> setBikeStatus());
    }


    private void setBikeStatus(){
        int UserId = 48;
        int statusId;
        if(RentOrReturn == 1)
        {
            statusId = 0;
        }
        else
        {
            statusId=1;
        }


            LiveData<Response> liveResponse = rentViewModel
                    .setStatus(UserId, statusId);

            liveResponse.observe(this, this::observeResponse);

    }

    private void observeResponse(@Nullable Response response){
        if(response != null) {
            if (response.isSuccessful()) {
                showToast("Rent/Return Successful");

                //navController.navigate(R.id.action_registerFragment_to_menuFragment);
            } else {
                showToast(response.toString());
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
