package ie.demo.inventorymanagement.interceptor;

import ie.demo.inventorymanagement.Context;
import ie.demo.inventorymanagement.ReturnContext;

// Concrete interceptor
// Due to the fact there is specific functionality
// associated with this interceptor it can only work with a ReturnContext object
public class CollectionSchedulerInterceptor implements Interceptor<ReturnContext> {

    @Override
    public void execute(ReturnContext context) {

    }
}
