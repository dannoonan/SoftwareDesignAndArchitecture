package com.cs4125.bikerentalapp.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cs4125.bikerentalapp.R;
import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.viewmodel.BikeViewModel;
import com.cs4125.bikerentalapp.web.ResponseBody;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.navigation.Navigation;

public class FindBikeFragment extends Fragment {

    private BikeViewModel bikeViewModel;
    private View v;
    private HashMap<String, ArrayList> incomingBikeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_find_bike, container, false);
        configureViewModel();
        requestList();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void configureViewModel(){
        bikeViewModel = ViewModelProviders.of(this).get(BikeViewModel.class);
        bikeViewModel.init(ServiceLocator.get(BikeRepository.class));
    }

    private void requestList(){
        LiveData<ResponseBody> liveResponse = null;
        liveResponse = bikeViewModel.getAllBikes();
        liveResponse.observe(this, this::observeResponse);
    }

    private void configureUiItems(){
        List<String> bikeList = new ArrayList<>();

        List<Object> allBikes = incomingBikeList.get("bikes");

        for(int i=0; i<allBikes.size(); i++){
            try {
                Object getrow = allBikes.get(i);
                LinkedTreeMap<Object, Object> t = (LinkedTreeMap) getrow;
                String id = t.get("bikeId").toString().substring(0, t.get("bikeId").toString().indexOf("."));
                String type = t.get("bikeType").toString();
                String node = t.get("nodeId").toString();
                String position;
                if(t.get("position")==null)
                    position = "0,0";
                else
                    position = t.get("position").toString();
                bikeList.add(id + "/" + type + "/" + node + "/" + position);
            }
            catch(Exception e){
                Log.e("Exception", "Catch"+e.getMessage());
            }
        }

        for (int i = 0; i < bikeList.size(); i++) {
            Button myButton = new Button(getContext());
            String[]temp2= bikeList.get(i).split("/");
            myButton.setText("BikeID :"+temp2[0]+"\nType :"+temp2[1]);
            myButton.setId(Integer.parseInt(temp2[0]));
            final int id_ = myButton.getId();

            LinearLayout layout = (LinearLayout) v.findViewById(R.id.linearLayout);
            layout.addView(myButton);

            Bundle bundle = new Bundle();
            bundle.putInt("id", id_);
            bundle.putString("type", temp2[1]);
            bundle.putString("node", temp2[2]);
            bundle.putString("position", temp2[3]);

            myButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                        R.id.action_findBikeFragment_to_bikeInformationFragment, bundle));


        }
    }

    private void observeResponse(@Nullable ResponseBody response){
        if(response != null) {
            if (response.getResponseCode() == 200) {
                incomingBikeList = response.getExtend();
                configureUiItems();
            }
        }
    }
}
