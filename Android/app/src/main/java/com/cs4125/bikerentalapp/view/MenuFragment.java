package com.cs4125.bikerentalapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
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
import com.cs4125.bikerentalapp.model.entity.UserType;
import com.cs4125.bikerentalapp.model.entity.Visitor.MenuVisitor;
import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;
import com.cs4125.bikerentalapp.repository.user.UserRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.viewmodel.UserViewModel;

import androidx.navigation.Navigation;

import static android.content.Context.MODE_PRIVATE;
import static com.cs4125.bikerentalapp.view.LoginFragment.MY_PREFS_NAME;


public class MenuFragment extends Fragment {

    private Button findBikesButton;
    private Button rentBikeButton;
    private Button returnBikeButton;
    private Button addBikeButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        configureUiItems(view);
        bindUiItems();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void configureUiItems(View view){
        findBikesButton = view.findViewById(R.id.buttonFindBikes);
        rentBikeButton = view.findViewById(R.id.buttonRentBike);
        returnBikeButton = view.findViewById(R.id.buttonReturnBike);
        addBikeButton = view.findViewById(R.id.buttonAddBike);

        Visitor visitor = new MenuVisitor();
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init(ServiceLocator.get(UserRepository.class));

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String username = prefs.getString("username", null);

        userViewModel.getUser(username).observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User user) {
                if(user!=null) {
                    UserType userType = new UserType(Integer.parseInt(user.getUserType()));
                    userType.getLevel().accept(visitor);
                    if(userType.getLevel().returnBoolean())
                        Toast.makeText(getContext(), "true", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "false", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindUiItems(){
        findBikesButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_findBikeFragment, null));
        rentBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_rentBikeFragment, null));
        returnBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_returnBikeFragment, null));
        addBikeButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_menuFragment_to_addBikeFragment, null));
    }
}
