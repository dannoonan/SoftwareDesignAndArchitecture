package com.cs4125.bikerentalapp.model.db_entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Node {
    @PrimaryKey
    int nodeId;
    double longitude;
    double latitude;
}
