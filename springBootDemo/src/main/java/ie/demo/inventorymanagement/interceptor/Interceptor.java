package ie.demo.inventorymanagement.interceptor;

import ie.demo.inventorymanagement.Context;

// Interceptor interface
public interface Interceptor<T extends Context> {

    void execute(T context);
}
