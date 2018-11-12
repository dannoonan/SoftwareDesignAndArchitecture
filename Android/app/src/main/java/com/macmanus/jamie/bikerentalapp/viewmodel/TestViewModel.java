package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macmanus.jamie.bikerentalapp.model.entity.GithubUser;
import com.macmanus.jamie.bikerentalapp.repository.TestRepository;


public class TestViewModel extends ViewModel {

    private LiveData<GithubUser> githubUser;
    private TestRepository repository;

    public TestViewModel(TestRepository repository){
        this.repository = repository;
    }

    public void init(String username) {
        if (this.githubUser != null) {
            return;
        }
        this.githubUser = repository.getGithubUser(username);
    }

    public LiveData<GithubUser> getGithubUser() {
        return this.githubUser;
    }
}
