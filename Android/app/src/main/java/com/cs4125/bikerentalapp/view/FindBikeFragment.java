package com.cs4125.bikerentalapp.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class FindBikeFragment extends Fragment {

    private ArrayList<String> bikeList;
    private BikeViewModel bikeViewModel;
    private View v;
    private HashMap<String, ArrayList> list;

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
        bikeList = new ArrayList<String>();

        ArrayList<Object> temp = list.get("bikes");

        for(int i=0; i<temp.size(); i++){
            Object getrow = temp.get(i);
            LinkedTreeMap<Object,Object> t = (LinkedTreeMap) getrow;
            String id = t.get("bikeId").toString().substring(0,t.get("bikeId").toString().indexOf("."));
            String type = t.get("bikeType").toString();
            String node = t.get("nodeId").toString();
            String position = t.get("position").toString();
            bikeList.add(id+","+type+","+node+","+position);
        }

        bikeList.add("3,2,-1,150,300");
        for (int i = 0; i < bikeList.size(); i++) {
            Button myButton = new Button(getContext());
            String[]temp2=bikeList.get(i).split(",");
            myButton.setText("BikeID :"+temp2[0]+"\nType :"+temp2[1]);
            myButton.setId(Integer.parseInt(temp2[0]));
            final int id_ = myButton.getId();

            LinearLayout layout = (LinearLayout) v.findViewById(R.id.linearLayout);
            layout.addView(myButton);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    /*myButton.setOnClickListener(Navigation.createNavigateOnClickListener(
                        R.id.action_findBikeFragment_to_bikeInformationFragment, null));*/

                    BikeInformationFragment bikeInformationFragment = new BikeInformationFragment(id_,temp2[1],temp2[2],temp2[3]);
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction()
                            .replace(R.id.findBikeFragment, bikeInformationFragment,bikeInformationFragment.getTag() )
                            .commit();
                }
            });

        }
    }

    private void observeResponse(@Nullable ResponseBody response){

        if(response != null) {
            if (response.getResponseCode() == 200) {
                //showToast("Successful");
                list = response.getExtend();
                configureUiItems();
            } else {
                //showToast("Rent/Return Failed");
            }
        }
    }
}
