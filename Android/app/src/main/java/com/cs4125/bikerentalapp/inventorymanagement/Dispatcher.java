package com.cs4125.bikerentalapp.inventorymanagement;

import com.cs4125.bikerentalapp.inventorymanagement.mapper.Interceptor;

import java.util.Collection;

// Dispatcher
public class Dispatcher {
    private Collection<Interceptor> interceptors;

    public Dispatcher(Collection<Interceptor> interceptors){
        this.interceptors = interceptors;
    }

    public void register(Interceptor i){
        interceptors.add(i);
    }

    public void unRegister(Interceptor i){
        interceptors.remove(i);
    }

    public void dispatchInterceptor(Context context){
        interceptors.forEach(i -> i.execute(context));
    }
}
