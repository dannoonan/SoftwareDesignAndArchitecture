package ie.demo.interceptorframework.interceptor;

import ie.demo.interceptorframework.Context;

// Interceptor interface
public interface Interceptor<T extends Context> {

    void execute(T context);
}
