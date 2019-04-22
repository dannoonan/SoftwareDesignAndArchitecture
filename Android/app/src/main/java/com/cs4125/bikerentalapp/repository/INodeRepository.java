package com.cs4125.bikerentalapp.repository;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.db_entity.Node;

import java.util.List;

interface INodeRepository {
    LiveData<List<Node>> getNodes();
}
