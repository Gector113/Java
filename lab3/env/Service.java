package org.fpm.di.env;


public interface Service {

    interface Component<T> {
        enum Lifecycle {
            SINGLETON, PROTOTYPE
        }

        Class<? extends T> getImplementation();

        Class<T> getClazz();

        T getInstance();

        Lifecycle getLifecycle();
    }
}
