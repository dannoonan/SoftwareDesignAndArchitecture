package com.macmanus.jamie.bikerentalapp.view;


import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.macmanus.jamie.bikerentalapp.R;
import com.macmanus.jamie.bikerentalapp.repository.UserRepository;
import com.macmanus.jamie.bikerentalapp.sl.ServiceLocator;
import com.macmanus.jamie.bikerentalapp.viewmodel.LoginViewModel;
import com.macmanus.jamie.bikerentalapp.web.ResponseBody;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


public class LoginFragment extends Fragment {

    private EditText usernameField;
    private EditText passwordField;
    private Button   loginButton;
    private Button goToRegisterButton;
    private NavController navController;
    private LoginViewModel loginViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        configureUiItems(view);
        configureServiceLocator();
        configureViewModel();

        return view;
    }

    private void configureServiceLocator(){
        LoginViewModel loginViewModel = new LoginViewModel(ServiceLocator.get(UserRepository.class));
        ServiceLocator.addServiceInstance(LoginViewModel.class, loginViewModel);
    }

    private void configureViewModel(){
        loginViewModel = ServiceLocator.get(LoginViewModel.class);
    }


    private void configureUiItems(View view) {
        bindUiItems(view);
        Navigation.setViewNavController(view, new NavController(getContext()));
        navController = NavHostFragment.findNavController(this);

        loginButton.setOnClickListener(view1 -> loginUser());
        goToRegisterButton.setOnClickListener(view1 -> goToRegisterScreen());
    }

    private void bindUiItems(View view){
        usernameField = view.findViewById(R.id.usernameTxt);
        passwordField = view.findViewById(R.id.passwordTxt);
        loginButton = view.findViewById(R.id.loginButton);
        goToRegisterButton = view.findViewById(R.id.goToRegister);
    }

    private void goToRegisterScreen(){
        navController.navigate(R.id.action_loginFragment_to_registerFragment);
    }

    private void loginUser(){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();


        LiveData<ResponseBody> liveResponse = loginViewModel
                .login(username, password);


        liveResponse.observe(this, this::observeResponse);



    }

    private void observeResponse(@Nullable ResponseBody response){
        if(response != null) {
            if (response.getResponseCode() == 200) {
                showToast("Login Successful");

                navController.navigate(R.id.action_loginFragment_to_menuFragment);
            } else {
                showToast("Login Failed");
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
