package com.jarylchng.reactivemongoexample.nettyresteasy;

import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import com.jarylchng.reactivemongoexample.common.ResteasyApplication;
import org.jboss.resteasy.core.ResteasyDeploymentImpl;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import javax.ws.rs.core.Application;

public class NettyResteasy extends Application {
    private static void startServer() {
        NettyJaxrsServer netty = new NettyJaxrsServer();
        ResteasyDeployment deployment = new ResteasyDeploymentImpl();
        deployment.setApplication(new ResteasyApplication());
        netty.setDeployment(deployment);
        netty.setHostname("localhost");
        netty.setPort(8080);
        netty.setRootResourcePath("/");
        netty.setSecurityDomain(null);
        netty.start();
    }

    public static void main(String[] args) {
        EmbedMongo.start();
        startServer();
    }
}

