package com.infoshareacademy.workshop;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Class you should got, not write.
 * Magic for embedded Tomcat - showing the classes and paths.
 */
class TaskList4000 {
    public static void main(String... args) throws Exception {
        initTomcat();
    }

    private static void initTomcat() throws LifecycleException, ServletException {
        var tomcat = new Tomcat();
        tomcat.setPort(8080);
        var webappDirLocation = new File("src/main/webapp/").getAbsolutePath();
        var ctx = tomcat.addWebapp("", webappDirLocation);
        ctx.addApplicationListener("com.infoshareacademy.workshop.utils.DbInit");
        handleResources(ctx);
        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * Magic for handling e.g. @WebServlet annotation.
     *
     * @param ctx Tomcat context
     * @see <a href="https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat#add-a-launcher-class">Heroku Tutorial</a>
     * @see <a href="https://stackoverflow.com/a/12688842/4774651">StackOverflow further explanation</a>
     */
    private static void handleResources(Context ctx) {
        var additionWebInfClasses = new File("target/classes").getAbsolutePath();
        var resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses, "/"));
        ctx.setResources(resources);
    }
}
