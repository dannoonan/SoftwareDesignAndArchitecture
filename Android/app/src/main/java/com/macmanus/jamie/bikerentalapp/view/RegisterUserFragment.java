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
import android.widget.EditText;
import android.widget.TextView;

import com.macmanus.jamie.bikerentalapp.R;

import androidx.navigation.Navigation;


public class RegisterUserFragment extends Fragment {

    String username;
    String cardNo;
    String email;
    String password;


    public RegisterUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_user, container, false);
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Button registerBtn = getView().findViewById(R.id.registerBtn);
        EditText usernameTxt = getView().findViewById(R.id.usernameTxt);
        EditText cardTxt = getView().findViewById(R.id.cardTxt);
        EditText emailTxt = getView().findViewById(R.id.emailTxt);
        EditText passwordTxt = getView().findViewById(R.id.passwordTxt);
        


        registerBtn.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_registerFragment_to_menuFragment, null));

    }

}
