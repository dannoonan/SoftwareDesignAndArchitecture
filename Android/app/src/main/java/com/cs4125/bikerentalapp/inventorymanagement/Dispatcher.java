package com.cs4125.bikerentalapp.inventorymanagement;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.cs4125.bikerentalapp.inventorymanagement.interceptor.Interceptor;

import java.util.Collection;

// Dispatcher
public class Dispatcher {
    private Collection<Interceptor> interceptors;

    public Dispatcher(Collection<Interceptor> interceptors){
        this.interceptors = interceptors;
    }

    public void register(Interceptor interceptor){
        interceptors.add(interceptor);
    }

    public void unRegister(Interceptor interceptor){
        interceptors.remove(interceptor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dispatchInterceptor(Context context){
        interceptors.forEach(i -> i.execute(context));
    }
}
