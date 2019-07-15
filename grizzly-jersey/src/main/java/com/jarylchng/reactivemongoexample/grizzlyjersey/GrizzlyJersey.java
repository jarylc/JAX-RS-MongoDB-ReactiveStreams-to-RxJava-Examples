package com.jarylchng.reactivemongoexample.grizzlyjersey;

import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class GrizzlyJersey {
    private static void startServer() {
        final ResourceConfig rc = new ResourceConfig()
                .packages("com.jarylchng.reactivemongoexample.grizzlyjersey")
                .packages("com.jarylchng.reactivemongoexample.common");
        GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/"), rc);
    }

    public static void main(String[] args) {
        EmbedMongo.start();
        startServer();
    }
}

