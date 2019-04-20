package com.cs4125.bikerentalapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cs4125.bikerentalapp.model.entity.BikeCredential;
import com.cs4125.bikerentalapp.model.entity.RentReturnDetails;
import com.cs4125.bikerentalapp.web.ResponseBody;
import com.cs4125.bikerentalapp.web.Webservice;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BikeRepository implements IBikeRepository{
    private final Webservice webservice;
    private final Executor executor;

    public BikeRepository(Webservice webservice, Executor executor){
        this.webservice = webservice;
        this.executor = executor;
    }


    public LiveData<ResponseBody> rentVehicle(int bikeId, int userId){
        MutableLiveData<ResponseBody> liveResponse = new MutableLiveData<>();

        executor.execute(() -> {
            Response<ResponseBody> response;
            try {
                response = webservice.rentVehicle(bikeId, userId).execute();
                liveResponse.postValue(response.body());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        });

        return liveResponse;
    }

    public LiveData<ResponseBody> returnVehicle(RentReturnDetails details){
        MutableLiveData<ResponseBody> liveResponse = new MutableLiveData<>();

        executor.execute(() -> {
            Response<ResponseBody> response;
            try {
                response = webservice.returnVehicle(
                        details.getUserId(),
                        details.getLatitude(),
                        details.getLongitude(),
                        details.getAmountPaid(),
                        details.getStudentCardId(),
                        details.getNodeId()).execute();
                liveResponse.postValue(response.body());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        });

        return liveResponse;
    }

    public LiveData<ResponseBody> getAllBikes(){
        MutableLiveData<ResponseBody> liveResponse = new MutableLiveData<>();

        executor.execute(() -> {
            Response<ResponseBody> response;
            try {
                response = webservice.getAllBikes().execute();
                liveResponse.postValue(response.body());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        });

        return liveResponse;
    }

    public LiveData<ResponseBody> addBike(BikeCredential credential){
        MutableLiveData<ResponseBody> liveResponse = new MutableLiveData<>();
        Call<ResponseBody> call = getAddBikeRequest(credential);

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

    private Call<ResponseBody> getAddBikeRequest(BikeCredential credential){
        return webservice.addBikes(
                credential.getBikeType(),
                Integer.parseInt(credential.getNodeId()));
    }

}