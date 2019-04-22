package com.cs4125.bikerentalapp.view;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
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
import com.cs4125.bikerentalapp.model.db_entity.User;
import com.cs4125.bikerentalapp.model.entity.UserCredential;
import com.cs4125.bikerentalapp.repository.user.UserRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.viewmodel.LoginViewModel;
import com.cs4125.bikerentalapp.viewmodel.UserViewModel;
import com.cs4125.bikerentalapp.web.ResponseBody;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {

    public static final String MY_PREFS_NAME = "PrefsFile";
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
        configureViewModel();
        return view;
    }

    private void configureViewModel(){
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.init(ServiceLocator.get(UserRepository.class));
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
        UserCredential credential = new UserCredential
                .Builder()
                .setUsername(usernameField.getText().toString())
                .setPassword(passwordField.getText().toString())
                .build();

        LiveData<ResponseBody> liveResponse = loginViewModel.login(credential);
        liveResponse.observe(this, this::observeResponse);
    }

    private void observeResponse(@Nullable ResponseBody response){
        if(response != null) {
            if (response.getResponseCode() == 200) {
                showToast("Login Successful");

                SharedPreferences.Editor editor = getActivity().
                        getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("username", usernameField.getText().toString());
                editor.apply();

                storeUserDetailsLocally(response);

                navController.navigate(R.id.action_loginFragment_to_menuFragment);
            } else {
                showToast("Login Failed");
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void storeUserDetailsLocally(ResponseBody response){
        Object keys[] = response.getExtend().keySet().toArray();
        String userTypeId = (String) response.getExtend().get("userTypeId: ");
        String userId = (String) response.getExtend().get("userId: ");
        String studentCardId = (String) response.getExtend().get("studentCardId: ");
        String username = (String) response.getExtend().get("username: ");
        String isBanned = (String) response.getExtend().get("isBanned: ");
        String email = (String) response.getExtend().get("email: ");

        User user = new User();
        user.setUserType(userTypeId);
        user.setUserId(Integer.parseInt(userId));
        user.setStudentCardId(studentCardId);
        user.setUsername(username);
        user.setBanned(Boolean.parseBoolean(isBanned));
        user.setEmail(email);

        UserViewModel userViewModel =  ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init(ServiceLocator.get(UserRepository.class));

        userViewModel.insertUser(user);
    }
}
