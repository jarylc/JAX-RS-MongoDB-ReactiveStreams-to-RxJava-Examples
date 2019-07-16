package com.jarylchng.reactivemongoexample.undertowresteasy;

import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import com.jarylchng.reactivemongoexample.common.CommonApplication;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import javax.ws.rs.core.Application;

public class UndertowResteasy extends Application {
    private static void startServer() {
        String host = "localhost";
        int port = 8080;

        Undertow.Builder builder = Undertow.builder().addHttpListener(port, host);
        UndertowJaxrsServer server = new UndertowJaxrsServer().start(builder);
        server.deploy(CommonApplication.class, "/");
    }

    public static void main(String[] args) {
        EmbedMongo.start();
        startServer();
    }
}

