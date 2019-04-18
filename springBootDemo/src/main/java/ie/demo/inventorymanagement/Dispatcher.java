package ie.demo.inventorymanagement;

import ie.demo.inventorymanagement.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.Collection;

// Dispatcher
public class Dispatcher<T extends Context> {
    private Collection<Interceptor<T>> interceptors;
    private Class type;

    Dispatcher(Context c){
        interceptors = new ArrayList<>();
        type = c.getClass();
    }

    void register(Interceptor<T> interceptor){
        interceptors.add(interceptor);
    }

    public void unRegister(Interceptor<T> interceptor){
        interceptors.remove(interceptor);
    }

    public void setInterceptors(Collection<Interceptor<T>> interceptors){
        this.interceptors = interceptors;
    }

    void dispatchInterceptor(T context){
        if(type == context.getClass()){
            interceptors.forEach(i -> i.execute(context));
        }
    }
}
