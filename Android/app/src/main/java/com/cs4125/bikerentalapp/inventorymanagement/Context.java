package com.cs4125.bikerentalapp.inventorymanagement;

// Context interface
public abstract class Context {
    private Framework framework;

    Context(Framework framework){
        this.framework = framework;
    }
}
