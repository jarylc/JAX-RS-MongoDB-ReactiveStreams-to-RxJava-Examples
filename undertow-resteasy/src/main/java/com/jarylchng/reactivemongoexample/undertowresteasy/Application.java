package com.jarylchng.reactivemongoexample.undertowresteasy;

import javax.ws.rs.ApplicationPath;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class Application extends javax.ws.rs.core.Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Collections.emptySet();
    }

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> classes = new HashSet<>();
        classes.add(new Resource());
        return classes;
    }
}
