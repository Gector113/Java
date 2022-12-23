package org.fpm.di.container;

public interface Container {
    <T> T getComponent(Class<T> clazz);
}
