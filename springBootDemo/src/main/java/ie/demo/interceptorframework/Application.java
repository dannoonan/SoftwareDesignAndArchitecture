package ie.demo.interceptorframework;

import ie.demo.interceptorframework.interceptor.CollectionSchedulerInterceptor;


import ie.demo.interceptorframework.interceptor.Interceptor;
import ie.demo.interceptorframework.interceptor.LoggingInterceptor;

import java.util.*;

// Interceptors are created and registered to dispatchers.
// If more interceptors are implemented, we must also register
// these interceptors here
public class Application {


    public Framework buildFramework(){
        Framework framework = new Framework();
        DispatcherFactory factory = new DispatcherFactory();
        Collection<Dispatcher<Context>> dispatchers = new ArrayList<>();

        Dispatcher rentDispatcher = factory.getDispatcher(new RentContext());
        Dispatcher returnDispatcher = factory.getDispatcher(new ReturnContext());
        Dispatcher reportDispatcher = factory.getDispatcher(new ReportContext());

        Interceptor<Context> loggingInterceptor = new LoggingInterceptor();
        Interceptor<ReturnContext> collectionsInterceptor = new CollectionSchedulerInterceptor();

        rentDispatcher.register(loggingInterceptor);
        reportDispatcher.register(loggingInterceptor);
        returnDispatcher.register(loggingInterceptor);
        returnDispatcher.register(collectionsInterceptor);

        dispatchers.add(rentDispatcher);
        dispatchers.add(returnDispatcher);
        dispatchers.add(reportDispatcher);

        framework.setDispatchers(dispatchers);
        framework.setNodeLocations(new HashMap<>());

        return framework;
    }

}
