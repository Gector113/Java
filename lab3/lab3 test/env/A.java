package org.fpm.di.env;

import javax.inject.Inject;

public class A {

    @Inject
    private B b;

    public B getDependency() {
        return b;
    }
}
