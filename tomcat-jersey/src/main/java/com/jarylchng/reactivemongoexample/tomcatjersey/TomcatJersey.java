package com.jarylchng.reactivemongoexample.tomcatjersey;

import com.jarylchng.reactivemongoexample.common.EmbedMongo;
import com.jarylchng.reactivemongoexample.common.ResourceReactive;
import com.jarylchng.reactivemongoexample.common.ResourceSynced;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.annotation.WebFilter;
import javax.ws.rs.core.Application;
import java.io.File;

@WebFilter(asyncSupported = true)
public class TomcatJersey extends Application {
    private static void startServer() throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        File base = new File(System.getProperty("java.io.tmpdir"));
        tomcat.setBaseDir(base.getAbsolutePath());
        tomcat.setPort(8080);

        Context context = tomcat.addWebapp("", base.getAbsolutePath());
        Tomcat.addServlet(context, "tomcat-jersey",
                new ServletContainer(new ResourceConfig()
                        .register(ResourceReactive.class)
                        .register(ResourceSynced.class)))
                .setAsyncSupported(true);
        context.addServletMappingDecoded("/*", "tomcat-jersey");

        tomcat.start();
        tomcat.getConnector();
    }

    public static void main(String[] args) throws LifecycleException {
        EmbedMongo.start();
        startServer();
    }
}

