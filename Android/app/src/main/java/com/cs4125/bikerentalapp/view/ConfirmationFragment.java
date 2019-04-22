package com.cs4125.bikerentalapp.view;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
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
import com.cs4125.bikerentalapp.model.commands.Command;
import com.cs4125.bikerentalapp.model.commands.Rent;
import com.cs4125.bikerentalapp.model.commands.Return;
import com.cs4125.bikerentalapp.model.db_entity.User;

import com.cs4125.bikerentalapp.model.entity.RentReturnDetails;
import com.cs4125.bikerentalapp.model.invokers.Invoker;
import com.cs4125.bikerentalapp.model.receivers.Bike;
import com.cs4125.bikerentalapp.model.receivers.Vehicle;
import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.repository.user.UserRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.viewmodel.UserViewModel;
import com.cs4125.bikerentalapp.web.ResponseBody;

import static android.content.Context.MODE_PRIVATE;
import static com.cs4125.bikerentalapp.view.LoginFragment.MY_PREFS_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationFragment extends Fragment {
    private String input = "";
    private Vehicle vehicle;
    private int rentOrReturn;
    private String vehicleType;
    private int vehicleId;
    private RentReturnDetails rentReturnDetails;
    private Invoker invoker;
    private static int userId=0;
    private static int studentCardId=0;
    private static Location location;


    public ConfirmationFragment() {
    }

    @SuppressLint("ValidFragment")
    public ConfirmationFragment(int ROrR, String in) {
        rentOrReturn = ROrR;
        input = in;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_confirm_rent, container, false);
        configureUiItems(v);

        return v;
    }

// comment

    private void configureUiItems(View v){

        createInvoker();

        TextView idText;
        TextView typeText;
        Button confirm = v.findViewById(R.id.confirmBtn);
        idText = v.findViewById(R.id.idText);
        typeText = v.findViewById(R.id.typeText);
        idText.setText("Vehicle ID: " + vehicleId);
        typeText.setText("Vehicle Type: "+ vehicleType);
        confirm.setOnClickListener(view1 -> setBikeStatus());
    }

    public void createInvoker(){
        String[] data = input.split(",");
        getDetails(data);
        makeVehicle();

        Command command;
        if(rentOrReturn==1){
            command = new Rent(vehicle);
            invoker = new Invoker(command);
        }else{
            command = new Return(vehicle);
            invoker = new Invoker(command);
        }
    }

    private void makeVehicle(){
        //Can extend this later to accommodate more vehicle types
        vehicle = new Bike(rentReturnDetails, ServiceLocator.get(BikeRepository.class));
    }

    public void getDetails(String[] data){
            getUserIds();
            getLocation();
        double latitude = 0;
        double longitude = 0;

            if(rentOrReturn==1){
                //Code to assign latitude and longitude to the node's location

            }else if(!location.equals(null)){
                latitude = getLatitude();
                longitude = getLongitude();
            }
            

            vehicleId = Integer.parseInt(data[0]);
            vehicleType = data[1];
            rentReturnDetails = new RentReturnDetails
                    .Builder()
                    .setVehicleId(vehicleId)
                    .setUserId(userId)
                    .setStudentCardId(studentCardId)
                    .setOrderId(0)
                    .setLatitude(latitude)
                    .setLongitude(longitude)
                    .setAmountPaid(0)
                    .setNodeId(1)
                    .build();
    }

    private double getLongitude() {
        return location.getLongitude();
    }

    private double getLatitude() {
        return location.getLatitude();
    }

    private void setBikeStatus(){
        LiveData<ResponseBody> liveResponse =  invoker.executeCommand();
        liveResponse.observe(this, this::observeResponse);
    }

    private void observeResponse(@Nullable ResponseBody response){
        if(response == null) {
            System.out.println("NULL_RESPONSE");
        }

        if(response != null) {
            if (response.getResponseCode() == 200) {
                showToast("rentVehicle/returnVehicle Successful");
            } else {
                showToast("rentVehicle/returnVehicle Failed");
            }
        }
    }

    public void getUserIds(){
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init(ServiceLocator.get(UserRepository.class));

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String username = prefs.getString("username", null);


        userViewModel.getUser(username).observe(this, user -> {
            if(user!=null){
                ConfirmationFragment.setUserId(user.getUserId());
                ConfirmationFragment.setStudentCardId(Integer.parseInt(user.getStudentCardId()));
            }else
                System.out.println("null");
        });

    }

    public static void setUserId(int id){
        userId = id;
    }

    public static void setStudentCardId(int id){
        studentCardId = id;
    }


    public void getLocation(){
        MainActivity main = (MainActivity) getActivity();
        location = main.getLocation();
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}