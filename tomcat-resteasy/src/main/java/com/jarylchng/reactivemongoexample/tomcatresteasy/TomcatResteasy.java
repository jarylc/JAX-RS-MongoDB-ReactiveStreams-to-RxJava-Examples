package com.jarylchng.reactivemongoexample.tomcatresteasy;

import com.jarylchng.reactivemongoexample.common.CommonApplication;
import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;

import javax.ws.rs.core.Application;
import java.io.File;

public class TomcatResteasy extends Application {
    private static void startServer() throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        File base = new File(System.getProperty("java.io.tmpdir"));
        tomcat.setBaseDir(base.getAbsolutePath());
        tomcat.setPort(8080);

        Context context = tomcat.addContext("", base.getAbsolutePath());
        context.addApplicationListener(ResteasyBootstrap.class.getName());
        Tomcat.addServlet(context, "tomcat-resteasy", new HttpServletDispatcher());
        context.addParameter("javax.ws.rs.Application", CommonApplication.class.getName());
        context.addServletMappingDecoded("/*", "tomcat-resteasy");

        tomcat.start();
        tomcat.getConnector();
    }

    public static void main(String[] args) throws LifecycleException {
        EmbedMongo.start();
        startServer();
    }
}

