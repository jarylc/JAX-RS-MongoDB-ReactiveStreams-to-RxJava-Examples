package com.jarylchng.reactivemongoexample.nettyjersey;

import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class NettyJersey {
    private static void startServer() {
        ResourceConfig resourceConfig = new ResourceConfig()
                .packages("com.jarylchng.reactivemongoexample.common");
        NettyHttpContainerProvider.createHttp2Server(
                URI.create("http://localhost:8080/"),
                resourceConfig, null);
    }

    public static void main(String[] args) {
        EmbedMongo.start();
        startServer();
    }
}

