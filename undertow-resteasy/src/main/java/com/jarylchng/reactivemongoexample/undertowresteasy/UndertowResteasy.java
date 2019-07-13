package com.jarylchng.reactivemongoexample.undertowresteasy;

import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

public class UndertowResteasy {
    private static void startServer() {
        String host = "localhost";
        int port = 8080;

        Undertow.Builder builder = Undertow.builder().addHttpListener(port, host);
        UndertowJaxrsServer server = new UndertowJaxrsServer().start(builder);
        server.deploy(Application.class, "/");
    }

    public static void main(String[] args) {
        EmbedMongo.start();
        startServer();
    }
}

