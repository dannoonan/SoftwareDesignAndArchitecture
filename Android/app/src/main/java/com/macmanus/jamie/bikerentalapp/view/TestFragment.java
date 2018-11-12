package com.macmanus.jamie.bikerentalapp.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macmanus.jamie.bikerentalapp.R;
import com.macmanus.jamie.bikerentalapp.model.entity.GithubUser;
import com.macmanus.jamie.bikerentalapp.repository.TestRepository;
import com.macmanus.jamie.bikerentalapp.sl.ServiceLocator;
import com.macmanus.jamie.bikerentalapp.viewmodel.TestViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {
    private TextView outputView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getUiItems();
        configureServiceLocator();
        configureViewModel();
    }

    private void getUiItems() {
        this.outputView = getView().findViewById(R.id.gitUserInfo);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    private void configureServiceLocator(){
        TestViewModel testViewModel = new TestViewModel(ServiceLocator.get(TestRepository.class));
        ServiceLocator.addServiceInstance(TestViewModel.class, testViewModel);
    }

    private void configureViewModel(){
        String username = "jamiemac96";
        TestViewModel viewModel;
        viewModel = ServiceLocator.get(TestViewModel.class);
        viewModel.init(username);
        viewModel.getGithubUser().observe(this, this::updateUI);
    }

    private void updateUI(@Nullable GithubUser user){
        this.outputView.setText("Avatar: " + user.getAvatar_url());
    }

}
