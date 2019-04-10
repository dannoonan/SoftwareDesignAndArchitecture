package com.cs4125.bikerentalapp.repository.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.cs4125.bikerentalapp.model.dao.UserDao;
import com.cs4125.bikerentalapp.model.db_entity.User;
import com.cs4125.bikerentalapp.model.entity.UserCredential;
import com.cs4125.bikerentalapp.web.ResponseBody;
import com.cs4125.bikerentalapp.web.Webservice;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements IUserRepository {
    private final Webservice webservice;
    private final UserDao userDao;
    private final Executor executor;
    //private LiveData<User> user;

    public UserRepository(Webservice webservice,
                          UserDao userDao,
                          Executor executor){
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;

        //UserDatabase db = UserDatabase.getDatabase(application);
        //mWordDao = db.wordDao();
    }

    public LiveData<User> getUser(String username) {


        Log.e("Test","Call");
        User user = userDao.load(username).getValue();
        if ((user == null)) {
        executor.execute(() -> {
                Response<User> response = null;
                Log.e("Test","Test");

                try {
                    response = webservice.getUser(username).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                userDao.insert(response.body());

            });
        }
        return userDao.load(username);
    }

    public LiveData<ResponseBody> registerUser(UserCredential credential){
        MutableLiveData<ResponseBody> liveResponse = new MutableLiveData<>();
        Call<ResponseBody> response = getRegisterRequest(credential);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                liveResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return liveResponse;
    }

    public LiveData<ResponseBody> loginUser(UserCredential credential){
        MutableLiveData<ResponseBody> liveResponse = new MutableLiveData<>();
        Call<ResponseBody> call = webservice.loginUser(
                credential.getUsername(),
                credential.getPassword());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                liveResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return liveResponse;
    }

    private Call<ResponseBody> getRegisterRequest(UserCredential credential){
        return webservice.registerUser(
                credential.getUsername(),
                credential.getPassword(),
                credential.getEmail(),
                credential.getUserType(),
                credential.getStudentCardId());

    }

    public void insertUser(User user) {


        executor.execute(() -> {
            userDao.insert(user);
        });

    }

    private static class UserInsertion extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private UserInsertion(UserDao userDao) {

            this.userDao = userDao;

        }

        @Override
        protected Void doInBackground(User... data) {


            return null;

        }

    }
}
