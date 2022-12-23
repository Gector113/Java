package org.fpm.di.env;

import javax.inject.Inject;

public class C {

    private B b;

    @Inject
    public C(B b) {
        this.b = b;
    }

    public B getDependency() {
        return b;
    }
}
