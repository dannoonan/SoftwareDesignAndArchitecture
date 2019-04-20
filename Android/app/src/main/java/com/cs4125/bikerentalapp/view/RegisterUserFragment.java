package com.cs4125.bikerentalapp.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs4125.bikerentalapp.R;
import com.cs4125.bikerentalapp.model.entity.UserCredential;
import com.cs4125.bikerentalapp.model.entity.UserType;
import com.cs4125.bikerentalapp.repository.user.UserRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.viewmodel.RegisterViewModel;
import com.cs4125.bikerentalapp.web.ResponseBody;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


public class RegisterUserFragment extends Fragment {

    private EditText usernameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText studentCardId;
    private Button   registerButton;
    private NavController navController;

    private RegisterViewModel registerViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_user, container, false);
        configureUiItems(view);
        configureViewModel();
        return view;
    }

    private void configureViewModel(){
        //FACTORY PATTERN
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerViewModel.init(ServiceLocator.get(UserRepository.class));
    }


    private void configureUiItems(View view) {
        bindUiItems(view);
        Navigation.setViewNavController(view, new NavController(getContext()));
        navController = NavHostFragment.findNavController(this);
        registerButton.setOnClickListener(view1 -> registerUser());
    }

    private void bindUiItems(View view){
        usernameField = view.findViewById(R.id.usernameTxt);
        emailField = view.findViewById(R.id.emailTxt);
        passwordField = view.findViewById(R.id.passwordTxt);
        studentCardId = view.findViewById(R.id.cardTxt);
        registerButton = view.findViewById(R.id.registerBtn);
    }

    private void registerUser(){
        UserCredential registerCredential = new UserCredential
                .Builder()
                .setUsername(usernameField.getText().toString())
                .setEmail(emailField.getText().toString())
                .setPassword(passwordField.getText().toString())
                .setStudentCardId(studentCardId.getText().toString())
                .setUserType(new UserType(1))
                .build();

        LiveData<ResponseBody> liveResponse = registerViewModel.register(registerCredential);

        //OBSERVER PATTERN
        liveResponse.observe(this, this::observeResponse);
    }

    private void observeResponse(@Nullable ResponseBody response){
        if(response != null) {
            if (response.getResponseCode() == 200) {
                showToast("Registration Successful");

                navController.navigate(R.id.action_registerFragment_to_menuFragment);
            } else {
                showToast("Registration Failed");
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
