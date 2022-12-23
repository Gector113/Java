package org.fpm.di.binder;

import org.fpm.di.env.ComponentService;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class DefaultBinderTest {

    private final ComponentService componentService = new ComponentService(new ArrayList<>());
    private final DefaultBinder binder = new DefaultBinder(componentService);

    @Test
    public void bindClassTest() {
        binder.bind(Single.class);
        assertSame(Single.class, componentService.getComponent(Single.class).getClazz());
    }

    @Test
    public void bindClassWithImplementationTest() {
        binder.bind(Single.class, SingleExt.class);
        binder.bind(SingleExt.class);
        assertSame(SingleExt.class, componentService.getComponent(Single.class).getClazz());
    }

    @Test
    public void bindClassWithInstanceTest() {
        Single single = new Single();
        binder.bind(Single.class, single);
        assertSame(single, componentService.getComponent(Single.class).getInstance());
    }
}