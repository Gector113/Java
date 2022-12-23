package org.fpm.di.env;

import org.fpm.di.binder.DefaultBinder;
import org.fpm.di.config.Configuration;
import org.fpm.di.container.Container;
import org.fpm.di.container.DefaultContainer;

import java.util.ArrayList;
import java.util.List;

public class Env implements Environment {

    private final List<ComponentService.Component<?>> components = new ArrayList<>();

    @Override
    public Container configure(Configuration configuration) {
        ComponentService componentService = new ComponentService(components);
        configuration.configure(new DefaultBinder(componentService));
        return new DefaultContainer(componentService);
    }
}
