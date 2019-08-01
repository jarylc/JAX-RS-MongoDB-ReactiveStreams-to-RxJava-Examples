package com.jarylchng.reactivemongoexample.nettyjersey;

import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import com.jarylchng.reactivemongoexample.common.ResourceReactive;
import com.jarylchng.reactivemongoexample.common.ResourceSynced;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class NettyJersey {
    private static void startServer() {
        ResourceConfig rc = new ResourceConfig()
                .register(new ResourceReactive())
                .register(new ResourceSynced());
        NettyHttpContainerProvider.createHttp2Server(
                URI.create("http://localhost:8080/"),
                rc, null);
    }

    public static void main(String[] args) {
        EmbedMongo.start();
        startServer();
    }
}

