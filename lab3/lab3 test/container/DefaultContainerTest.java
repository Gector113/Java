package org.fpm.di.container;

import org.fpm.di.binder.DefaultBinder;
import org.fpm.di.env.ComponentService;
import org.fpm.di.env.Env;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DefaultContainerTest {
    private final List<ComponentService.Component<?>> components = new ArrayList<>();
    private final DefaultContainer container = new DefaultContainer(new ComponentService(components));
    private final DefaultBinder binder = new DefaultBinder(new ComponentService(components));

    @Test
    public void getComponentTest() {
        Object obj = new Object();
        ComponentService.Component<Object> component = new ComponentService.Component<>();
        component.setLifecycle(ComponentService.Component.Lifecycle.SINGLETON);
        component.setInstance(obj);
        component.setClazz(Object.class);
        components.add(component);
        Assert.assertSame(obj, container.getComponent(Object.class));
    }
}