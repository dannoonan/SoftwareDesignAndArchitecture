package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public interface ILevel {
    String getType();
    void accept(Visitor v);
}
