package core;
import org.apache.jasper.servlet.JspServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import servlets.DemoServlet;
import servlets.EscanearPuertoServlet;
import servlets.HelloServlet;

class RunServer {
    public static void main(String args[]) {

        System.out.println("Initializing server...");
        final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase("webapp");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());

        context.addServlet(DefaultServlet.class, "/");

        final ServletHolder jsp = context.addServlet(JspServlet.class, "*.jsp");
        jsp.setInitParameter("classpath", context.getClassPath());
        
        // add your own additional servlets like this:
        // context.addServlet(JSONServlet.class, "/json");
        context.addServlet(new ServletHolder(new HelloServlet()),"/hello");
        
        context.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")),"/it/*");
        context.addServlet(new ServletHolder(new DemoServlet()),"/demoServlet");
        context.addServlet(new ServletHolder(new EscanearPuertoServlet()),"/escanearPuerto");
        
        final Server server = new Server(8080);
        server.setHandler(context);

        System.out.println("Starting server...");
        try {
            server.start();
        } catch(Exception e) {
            System.out.println("Failed to start server!");
            return;
        }

        System.out.println("Server running...");
        while(true) {
            try {
                server.join();
            } catch(InterruptedException e) {
                System.out.println("Server interrupted!");
            }
        }
    }
}