package org.fpm.di.container;

import org.fpm.di.env.ComponentService;

public class DefaultContainer implements Container {
    private final ComponentService componentService;

    public DefaultContainer(ComponentService componentService) {
        this.componentService = componentService;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        ComponentService.Component<T> component = componentService.getComponent(clazz);
        if (component.getLifecycle().equals(ComponentService.Component.Lifecycle.SINGLETON)) {
            return component.getInstance();
        } else {
            return componentService.createInstance(component.getClazz());
        }
    }
}
