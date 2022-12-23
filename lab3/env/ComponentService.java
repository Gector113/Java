package org.fpm.di.env;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class ComponentService implements Service {
    private final List<Component<?>> components;

    public ComponentService(List<Component<?>> components) {
        this.components = components;
    }

    public <T> T createInstance(Class<T> clazz) {
        T instance;
        try {
            Optional<Constructor<?>> injectedConstr = Arrays.stream(clazz.getConstructors())
                    .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                    .findAny();
            if (injectedConstr.isPresent()) {
                instance = instanceInjectedByConstructor(injectedConstr.get());
            } else {
                instance = instanceInjectedByFields(clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return instance;
    }

    private <T> T instanceInjectedByConstructor(Constructor<?> constructor) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Map<Class<?>, Object> params = new LinkedHashMap<>();
        for (Class<?> parameterType : constructor.getParameterTypes()) {
            params.put(parameterType, components.stream()
                    .filter(component -> component.getClazz().isAssignableFrom(parameterType))
                    .map(component -> {
                        if (component.getImplementation() != null) {
                            return components.stream()
                                    .filter(comp -> comp.getClazz().equals(component.getImplementation()))
                                    .map(ComponentService.Component::getInstance)
                                    .findAny().orElseThrow();
                        } else return component.getInstance();
                    }).findAny().orElse(null));

        }
        return (T) constructor.newInstance(params.values().toArray());
    }

    private <T> T instanceInjectedByFields(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T instance = clazz.getConstructor().newInstance();
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        for (Field field : fields) {
            Object injectedObj = components.stream()
                    .filter(comp -> comp.getClazz().isAssignableFrom(field.getType()))
                    .map(Component::getInstance)
                    .findFirst().orElse(null);
            if (injectedObj != null) {
                field.setAccessible(true);
                field.set(instance, injectedObj);
            }
        }
        return instance;
    }

    public <T> void setLifecycle(Component<T> component, Class<T> clazz) {
        if (clazz.isAnnotationPresent(Singleton.class)) {
            component.setLifecycle(Component.Lifecycle.SINGLETON);
            try {
                component.setInstance(clazz.getConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else component.setLifecycle(Component.Lifecycle.PROTOTYPE);
    }

    public <T> boolean isClassBound(Class<T> clazz) {
        return components.stream().anyMatch(component -> component.getClazz().equals(clazz));
    }

    public <T> Component<T> getComponent(Class<T> clazz) {
        Optional<Component<T>> component = components.stream().filter(comp -> comp.getClazz().equals(clazz)).map(comp -> (ComponentService.Component<T>) comp).findAny();
        if (component.isPresent()) {
            if (component.get().getImplementation() != null) {
                return (Component<T>) components.stream().filter(comp -> comp.getClazz().equals(component.get().getImplementation())).findAny().orElseThrow();
            }
        }
        return component.orElseThrow();
    }

    public <T> void addComponent(Component<T> component) {
        components.add(component);
    }

    public <T> void setComponentInstance(Class<T> componentClass, T instance) {
        components.stream()
                .filter(component -> component.getClazz().equals(componentClass))
                .map(component -> (Component<T>) component)
                .forEach(component -> component.setInstance(instance));
    }

    public static class Component<T> implements Service.Component<T> {
        private Class<T> clazz;
        private Class<? extends T> implementation;
        private T instance;
        private Lifecycle lifecycle;


        @Override
        public Class<? extends T> getImplementation() {
            return implementation;
        }

        public void setImplementation(Class<? extends T> implementation) {
            this.implementation = implementation;
        }

        @Override
        public Class<T> getClazz() {
            return clazz;
        }

        public void setClazz(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public T getInstance() {
            return instance;
        }

        public void setInstance(T instance) {
            this.instance = instance;
        }

        @Override
        public Lifecycle getLifecycle() {
            return lifecycle;
        }

        public void setLifecycle(Lifecycle lifecycle) {
            this.lifecycle = lifecycle;
        }
    }
}
