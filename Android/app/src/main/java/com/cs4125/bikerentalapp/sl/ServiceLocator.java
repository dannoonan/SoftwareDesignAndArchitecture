package com.cs4125.bikerentalapp.sl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Code sampled from code outlined in article:
 * https://medium.com/inloopx/service-locator-pattern-in-android-af3830924c69
 *
 * Github: https://gist.github.com/DanielNovak/06bc27fa4ecea63207c424bef88199df
 * */

//SERVICELOCATOR PATTERN

@SuppressWarnings({"unused", "WeakerAccess"})
public class ServiceLocator {

    private static final Map<String, Object> sServicesInstances = new HashMap<>();
    private static final Map<String, Class> sServicesImplementationsMapping = new HashMap<>();
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static final Object sServicesInstancesLock = new Object();

    public static void init(@NonNull Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * returnVehicle instance of desired class or object that implement desired interface.
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T get(@NonNull Class<T> clazz) {
        @SuppressWarnings("ResourceType") T instance = (T) getService(clazz.getName(), mContext);
        return instance;
    }

    /**
     * This method allows to bind a custom service implementation to an interface.
     *
     * @param interfaceClass      interface
     * @param implementationClass class which implement interface specified in first param
     */
    public static void bindCustomServiceImplementation(@NonNull Class interfaceClass, @NonNull Class implementationClass) {
        synchronized (sServicesInstancesLock) {
            sServicesImplementationsMapping.put(interfaceClass.getName(), implementationClass);
        }
    }

    public static void addServiceInstance(@NonNull Class interfaceClass, @NonNull Object o){
        sServicesInstances.put(interfaceClass.getName(), o);
    }

    @NonNull
    private static Object getService(@NonNull String name, @Nullable Context applicationContext) {
        Object result = sServicesInstances.get(name);

        synchronized (sServicesInstancesLock) {
            Object o = sServicesInstances.get(name);
            if (o != null) {
                return o;
            } else {
                try {
                    Object serviceInstance;
                    final Class<?> clazz;
                    if (sServicesImplementationsMapping.containsKey(name)) {
                        clazz = sServicesImplementationsMapping.get(name);
                    } else {
                        clazz = Class.forName(name);
                    }

                    try {
                        Constructor e1 = clazz.getConstructor(Context.class);
                        serviceInstance = e1.newInstance(applicationContext);
                    } catch (NoSuchMethodException var6) {
                        Constructor constructor = clazz.getConstructor();
                        serviceInstance = constructor.newInstance();
                    }
                    sServicesInstances.put(name, serviceInstance);
                    return serviceInstance;
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("Requested service class was not found: " + name, e);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Cannot initialize requested service: " + name, e);
                }
            }
        }
    }

    public interface Creator<T> {
        T newInstance(@NonNull Context context);
    }
}
