package org.fpm.di.example;


import org.fpm.di.container.Container;
import org.fpm.di.env.Env;
import org.fpm.di.env.Environment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Example {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new Env();
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() {
        /*
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }

    @Test
    public void test() {
        A a = container.getComponent(A.class);
        B b = container.getComponent(B.class);
        UseA useA = container.getComponent(UseA.class);
        assertNotNull(a);
        assertNotNull(b);
        assertSame(a, useA.getDependency());
    }
}
