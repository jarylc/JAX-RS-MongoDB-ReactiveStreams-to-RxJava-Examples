package com.jarylchng.reactivemongoexample.grizzlyjersey;

import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import com.jarylchng.reactivemongoexample.common.ResourceReactive;
import com.jarylchng.reactivemongoexample.common.ResourceSynced;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class GrizzlyJersey {
    private static void startServer() {
        ResourceConfig rc = new ResourceConfig()
                .register(ResourceReactive.class)
                .register(ResourceSynced.class);
        GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/"), rc);
    }

    public static void main(String[] args) {
        EmbedMongo.start();
        startServer();
    }
}

