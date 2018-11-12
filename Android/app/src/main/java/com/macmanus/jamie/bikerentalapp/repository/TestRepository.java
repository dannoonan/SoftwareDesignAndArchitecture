package com.macmanus.jamie.bikerentalapp.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.macmanus.jamie.bikerentalapp.model.dao.GithubUserDao;
import com.macmanus.jamie.bikerentalapp.model.entity.GithubUser;
import com.macmanus.jamie.bikerentalapp.web.Webservice;

import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRepository {

    private final Webservice webservice;
    private final GithubUserDao githubUserDao;
    private final Executor executor;

    public TestRepository(Webservice webservice, GithubUserDao githubUserDao, Executor executor){
        this.webservice = webservice;
        this.githubUserDao = githubUserDao;
        this.executor = executor;
    }

    public LiveData<GithubUser> getGithubUser(String userId) {

        updateDataFromWeb(userId);

        return githubUserDao.load(userId);
    }

        private void updateDataFromWeb(String userId){

        executor.execute(() -> {

            // TODO: implement method to check the last time that the githubUserDao info has
            // TODO: been updated. If it was longer than we 3 minutes, set userExists to
            // TODO: false and call the webservice instead of the local DB
            boolean userExists = false;
            // If user have to be updated
            if (!userExists) {
                webservice.getGithubUserData(userId).enqueue(new Callback<GithubUser>() {
                    @Override
                    public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {

                        Log.e("RESPONSE: ", response.body().getLogin());
                        executor.execute(() -> {
                               GithubUser user = response.body();
                               githubUserDao.save(user);
                        });
                    }

                    @Override
                    public void onFailure(Call<GithubUser> call, Throwable t) {
                        Log.e("NETWORK REQUEST", "FAILED");
                    }
                });
            }
        });

    }
}
