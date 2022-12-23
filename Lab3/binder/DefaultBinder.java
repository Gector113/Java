package org.fpm.di.binder;

import org.fpm.di.env.ComponentService;


public class DefaultBinder implements Binder {

    ComponentService componentService;

    public DefaultBinder(ComponentService componentService) {
        this.componentService = componentService;
    }

    @Override
    public <T> void bind(Class<T> clazz) {
        if (!componentService.isClassBound(clazz)) {
            ComponentService.Component<T> component = new ComponentService.Component<>();
            component.setClazz(clazz);
            component.setInstance(componentService.createInstance(clazz));
            componentService.setLifecycle(component, clazz);
            componentService.addComponent(component);
        }
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        ComponentService.Component<T> component = new ComponentService.Component<>();
        component.setClazz(clazz);
        component.setImplementation(implementation);
        componentService.setLifecycle(component, clazz);
        componentService.addComponent(component);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        if (!componentService.isClassBound(clazz)) {
            ComponentService.Component<T> component = new ComponentService.Component<>();
            component.setInstance(instance);
            component.setClazz(clazz);
//            if (clazz.isAnnotationPresent(Singleton.class)) {
//                component.setLifecycle(Env.Component.Lifecycle.SINGLETON);
//            } else component.setLifecycle(Env.Component.Lifecycle.PROTOTYPE);
            component.setLifecycle(ComponentService.Component.Lifecycle.SINGLETON);
            componentService.addComponent(component);
        } else {
            componentService.setComponentInstance(clazz, instance);
        }
    }


}
