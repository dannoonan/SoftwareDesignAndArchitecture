package com.cs4125.bikerentalapp.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cs4125.bikerentalapp.model.db_entity.Node;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NodeDao {
    @Insert(onConflict = REPLACE)
    void insertNode(Node user);

    @Insert(onConflict = REPLACE)
    void insertAllNodes(List<Node> nodes);

    @Query("SELECT * FROM node")
    LiveData<List<Node>>getNodes();

    @Query("SELECT * FROM node WHERE nodeId LIKE :nodeId")
    LiveData<List<Node>>getNodes(int nodeId);
}
