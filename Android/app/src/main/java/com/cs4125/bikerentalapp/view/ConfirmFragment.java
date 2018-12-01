package com.cs4125.bikerentalapp.view;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cs4125.bikerentalapp.R;
import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.viewmodel.RentViewModel;
import com.cs4125.bikerentalapp.web.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {


    private String input = "";
    private int rentOrReturn;
    private int bikeId;
    Button confirm;
    private RentViewModel rentViewModel;

    public ConfirmFragment() {
    }

    @SuppressLint("ValidFragment")
    public ConfirmFragment(int ROrR, String in) {
        rentOrReturn = ROrR;
        input = in;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_confirm_rent, container, false);

        configureUiItems(v);
        configureViewModel();

        return v;
    }

    private void configureUiItems(View v){
        String[] data = input.split(",");
        TextView idText;
        TextView typeText;
        bikeId = Integer.parseInt(data[0]);
        confirm = v.findViewById(R.id.confirmBtn);
        idText = v.findViewById(R.id.idText);
        typeText = v.findViewById(R.id.typeText);
        idText.setText("Bike ID: "+ data[0]);
        typeText.setText("Bike Type: "+ data[1]);
        confirm.setOnClickListener(view1 -> setBikeStatus());
    }

    private void configureViewModel(){
        rentViewModel = ViewModelProviders.of(this).get(RentViewModel.class);
        rentViewModel.init(ServiceLocator.get(BikeRepository.class));
    }


    private void setBikeStatus(){
        LiveData<ResponseBody> liveResponse = null;
        if(rentOrReturn == 1){
            liveResponse = rentViewModel.rentBike(bikeId, 46);
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