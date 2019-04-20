package com.cs4125.bikerentalapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cs4125.bikerentalapp.model.dao.NodeDao;
import com.cs4125.bikerentalapp.model.db_entity.Node;
import com.cs4125.bikerentalapp.web.ResponseBody;
import com.cs4125.bikerentalapp.web.Webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public class NodeRepository implements INodeRepository {
    private Webservice webservice;
    private NodeDao nodeDao;
    private Executor executor;

    public NodeRepository(Webservice webservice, NodeDao nodeDao, Executor executor){
        this.webservice = webservice;
        this.nodeDao = nodeDao;
        this.executor = executor;
    }

    @Override
    public LiveData<List<Node>> getNodes() {
        MutableLiveData<List<Node>> liveResponse = new MutableLiveData<>();

        executor.execute(()-> {
            List<Node> nodes = nodeDao.getNodes().getValue();
            if(nodes == null || nodes.isEmpty()){
                try{
                    ResponseBody response = webservice.getAllNodes().execute().body();
                    nodes = getNodesFromResponse(response);
                    nodeDao.insertAllNodes(nodes);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            liveResponse.postValue(nodes);
        });

        return liveResponse;
    }

    private List<Node> getNodesFromResponse(ResponseBody response){
        if (response.getResponseCode() != 200) return null;

        Map extend = response.getExtend();
        List<Map> responseNodes = (List) extend.get("nodes");
        List<Node> nodes = new ArrayList<>();

        if (responseNodes != null) {
            for(Map node: responseNodes){
                int id = Integer.parseInt(Objects.requireNonNull(node.get("nodeId")).toString());
                String position = (String)node.get("position");
                String[] latlong = Objects.requireNonNull(position).split(",");
                nodes.add(new Node(id, Integer.parseInt(latlong[0]), Integer.parseInt(latlong[1])));
            }
        }

        return nodes;
    }
}
