package org.fpm.di.env;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ComponentServiceTest {
    private ComponentService service;
    private List<ComponentService.Component<?>> components = new ArrayList<>();

    @Before
    public void sutUp() {
        service = new ComponentService(components);
    }

    @Test
    public void createInstanceTest() {
        assertNotNull(service.createInstance(Prototype.class));
        assertNotNull(service.createInstance(Single.class));
    }

    @Test
    public void createInjectedByFieldInstanceTest() {
        ComponentService.Component<A> component = new ComponentService.Component<>();
        component.setClazz(A.class);
        ComponentService.Component<B> component1 = new ComponentService.Component<>();
        component1.setClazz(B.class);
        component1.setInstance(new B());
        components.add(component1);
        A a = service.createInstance(A.class);
        assertNotNull(a);
        assertNotNull(a.getDependency());
    }

    @Test
    public void createInjectedByConstructorInstanceTest() {
        ComponentService.Component<C> component = new ComponentService.Component<>();
        component.setClazz(C.class);
        ComponentService.Component<B> component1 = new ComponentService.Component<>();
        component1.setClazz(B.class);
        component1.setInstance(new B());
        components.add(component1);
        C c = service.createInstance(C.class);
        assertNotNull(c);
        assertNotNull(c.getDependency());
    }

    @Test
    public void setLifecycleTest() {
        ComponentService.Component<Single> singleComponent = new ComponentService.Component<>();
        service.setLifecycle(singleComponent, Single.class);
        ComponentService.Component<Prototype> prototypeComponent = new ComponentService.Component<>();
        service.setLifecycle(prototypeComponent, Prototype.class);

        assertEquals(ComponentService.Component.Lifecycle.PROTOTYPE, prototypeComponent.getLifecycle());
        assertEquals(ComponentService.Component.Lifecycle.SINGLETON, singleComponent.getLifecycle());

    }

    @Test
    public void isClassBoundTest() {
        ComponentService.Component<Single> singleComponent = new ComponentService.Component<>();
        singleComponent.setClazz(Single.class);
        service.addComponent(singleComponent);

        assertTrue(service.isClassBound(Single.class));
        assertFalse(service.isClassBound(Prototype.class));
    }

    @Test
    public void getComponentTest() {
        ComponentService.Component<Single> singleComponent = new ComponentService.Component<>();
        singleComponent.setClazz(Single.class);
        service.addComponent(singleComponent);

        assertEquals(singleComponent, service.getComponent(Single.class));
    }

    @Test
    public void setComponentInstanceTest() {
        ComponentService.Component<Single> singleComponent = new ComponentService.Component<>();
        singleComponent.setClazz(Single.class);
        service.addComponent(singleComponent);
        service.setComponentInstance(Single.class, new Single());
        assertNotNull(singleComponent.getInstance());
    }
}