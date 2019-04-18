package ie.demo.inventorymanagement;

import ie.demo.inventorymanagement.interceptor.Interceptor;

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

    public void dispatchInterceptor(Context context){
        interceptors.forEach(i -> i.execute(context));
    }
}
