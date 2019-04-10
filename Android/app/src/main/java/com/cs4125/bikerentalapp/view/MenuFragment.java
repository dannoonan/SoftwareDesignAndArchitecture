package com.cs4125.bikerentalapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cs4125.bikerentalapp.R;
import com.cs4125.bikerentalapp.model.db_entity.User;
import com.cs4125.bikerentalapp.repository.user.UserRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.viewmodel.UserViewModel;

import androidx.navigation.Navigation;


public class MenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init(ServiceLocator.get(UserRepository.class));

        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User user) {
                // Update the cached copy of the words in the adapter.
                if(user!=null) {
                    Toast.makeText(getContext(), user.getStudentCardId(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext(), "NOPE", Toast.LENGTH_SHORT).show();
            }
        });

        Button findBikesButton = getView().findViewById(R.id.buttonFindBikes);
        Button rentBikeButton = getView().findViewById(R.id.buttonRentBike);
        Button returnBikeButton = getView().findViewById(R.id.buttonReturnBike);

        findBikesButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_findBikeFragment, null));
        rentBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_rentBikeFragment, null));
        returnBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_returnBikeFragment, null));
    }
}
