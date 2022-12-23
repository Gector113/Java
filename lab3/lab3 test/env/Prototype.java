package org.fpm.di.env;

import javax.inject.Inject;

public class Prototype {
    private Single single;

    @Inject
    public Prototype(Single single) {
        this.single = single;
    }

    public Prototype() {

    }

    public Single getSingle() {
        return single;
    }
}
