package com.macmanus.jamie.bikerentalapp.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.macmanus.jamie.bikerentalapp.R;

import androidx.navigation.Navigation;

/**
 */
public class LoginFragment extends Fragment {
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Button loginButton = getView().findViewById(R.id.loginButton);
        TextView goToRegisterButton = getView().findViewById(R.id.goToRegister);


        loginButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_loginFragment_to_menuFragment, null));
        goToRegisterButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_loginFragment_to_registerFragment, null));
    }
}
