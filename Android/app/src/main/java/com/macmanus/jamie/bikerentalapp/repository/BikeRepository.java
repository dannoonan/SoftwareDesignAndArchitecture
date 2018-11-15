package com.macmanus.jamie.bikerentalapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.macmanus.jamie.bikerentalapp.web.ResponseBody;
import com.macmanus.jamie.bikerentalapp.web.Webservice;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Response;

public class BikeRepository {
    private final Webservice webservice;
    private final Executor executor;

    public BikeRepository(Webservice webservice, Executor executor){
        this.webservice = webservice;
        this.executor = executor;
    }


    public LiveData<Response> setBikeStatus(int UserId, int StatusId){

        MutableLiveData<Response> liveResponse = new MutableLiveData<>();

        executor.execute(() -> {
            Response response;

            try {
                response = webservice.
                        setBikeStatus(UserId, StatusId).execute();

                liveResponse.postValue(response);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        });

        return liveResponse;
    }

    public LiveData<ResponseBody> placeOrder(String username, int bikeId, int paid){

        MutableLiveData<ResponseBody> liveResponse = new MutableLiveData<>();

        executor.execute(() -> {
            Response<ResponseBody> response;

            try {
                response = webservice.
                        placeOrder(username, bikeId, paid).execute();

                liveResponse.postValue(response.body());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        });

        return liveResponse;
    }
}
