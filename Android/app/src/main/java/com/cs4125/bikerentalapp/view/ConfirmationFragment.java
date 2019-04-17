package com.cs4125.bikerentalapp.view;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.Observer;
import android.content.SharedPreferences;
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
import com.cs4125.bikerentalapp.viewmodel.RentViewModel;
import com.cs4125.bikerentalapp.viewmodel.ReturnViewModel;
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
    private Command command;
    private Invoker invoker;
    private Button confirm;
    private RentViewModel rentViewModel;
    private ReturnViewModel returnViewModel;
    private static int userId=0;

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
        configureViewModel();

        return v;
    }

    private void configureUiItems(View v){

        createInvoker();

        TextView idText;
        TextView typeText;
        confirm = v.findViewById(R.id.confirmBtn);
        idText = v.findViewById(R.id.idText);
        typeText = v.findViewById(R.id.typeText);
        idText.setText("Vehicle ID: " + vehicleId);
        typeText.setText("Vehicle Type: "+ vehicleType);
        confirm.setOnClickListener(view1 -> setBikeStatus());
    }

    private void configureViewModel(){
        rentViewModel = ViewModelProviders.of(this).get(RentViewModel.class);
        rentViewModel.init(ServiceLocator.get(BikeRepository.class));
    }


    public void createInvoker(){
        String[] data = input.split(",");
        getDetails(data);
        makeVehicle(vehicleId, vehicleType);

        if(rentOrReturn==1){
            command = new Rent(vehicle);
            invoker = new Invoker(command);
        }else{
            command = new Return(vehicle);
            invoker = new Invoker(command);
        }
    }

    private void makeVehicle(int vehicleId, String vehicleType){
        //Can extend this later to accommodate more vehicle types
        vehicle = new Bike( rentReturnDetails, ServiceLocator.get(BikeRepository.class));
    }

    public void getDetails(String[] data){
            getUserId();
            vehicleId = Integer.parseInt(data[0]);
            vehicleType = data[7];
            rentReturnDetails = new RentReturnDetails
                    .Builder()
                    .setVehicleId(Integer.parseInt(data[0]))
                    .setUserId(userId)
                    .setStudentCardId(Integer.parseInt(data[1]) )
                    .setOrderId(Integer.parseInt(data[2]))
                    .setLatitude(Integer.parseInt(data[3]))
                    .setLongitude(Integer.parseInt(data[4]))
                    .setAmountPaid(Integer.parseInt(data[5]))
                    .setNodeId(Integer.parseInt(data[6]))
                    .build();
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
                showToast("Rent/Return Successful");
            } else {
                showToast("Rent/Return Failed");
            }
        }
    }

    public void getUserId(){
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init(ServiceLocator.get(UserRepository.class));

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String username = prefs.getString("username", null);


        userViewModel.getUser(username).observe(this, new Observer<User>(){
            @Override
            public void onChanged(@Nullable final User user){
                if(user!=null){
                    ConfirmationFragment.setUserId(user.getUserId());
                }else
                    System.out.println("null");
            }
        });

    }

    public static void setUserId(int id){
        userId = id;
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}