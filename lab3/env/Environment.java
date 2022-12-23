package org.fpm.di.env;

import org.fpm.di.config.Configuration;
import org.fpm.di.container.Container;

public interface Environment {
    Container configure(Configuration configuration);
}
