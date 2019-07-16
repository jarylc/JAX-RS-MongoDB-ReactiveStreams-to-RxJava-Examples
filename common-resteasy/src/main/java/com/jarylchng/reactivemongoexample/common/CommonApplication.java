package com.jarylchng.reactivemongoexample.common;

import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CommonApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Collections.emptySet();
    }

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> classes = new HashSet<>();
        classes.add(new ResourceReactive());
        classes.add(new ResourceSynced());
        return classes;
    }
}
