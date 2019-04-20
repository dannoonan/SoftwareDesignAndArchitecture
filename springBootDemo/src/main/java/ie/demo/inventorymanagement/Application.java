package ie.demo.inventorymanagement;

import ie.demo.inventorymanagement.interceptor.Interceptor;
import ie.demo.inventorymanagement.interceptor.LoggingInterceptor;

import java.util.ArrayList;
import java.util.Collection;

// Interceptors are created and registered to dispatchers.
// If more interceptors are implemented, we must also register
// these interceptors here
class Application {

    Framework buildFramework(){
        Framework framework = new Framework();
        DispatcherFactory factory = new DispatcherFactory();
        Collection<Dispatcher<Context>> dispatchers = new ArrayList<>();

        Dispatcher rentDispatcher = factory.getDispatcher(new RentContext());
        Dispatcher returnDispatcher = factory.getDispatcher(new ReturnContext());
        Dispatcher reportDispatcher = factory.getDispatcher(new ReportContext());

        Interceptor<Context> i = new LoggingInterceptor();

        rentDispatcher.register(i);
        reportDispatcher.register(i);
        returnDispatcher.register(i);

        dispatchers.add(rentDispatcher);
        dispatchers.add(returnDispatcher);
        dispatchers.add(reportDispatcher);

        framework.setDispatchers(dispatchers);

        return framework;
    }

}
