package fr.insee.rmes.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.apache.logging.log4j.web.Log4jWebSupport;

public class Log4j2ServletContextListener implements ServletContextListener {

    private static Logger logger = LogManager.getLogger(Log4j2ServletContextListener.class);
    private String log4j2ConfigFile;

    private Log4jServletContextListener listener;

    public Log4j2ServletContextListener() {
        this.listener = new Log4jServletContextListener();
        try {
            this.getEnvironmentProperties();
        }
        catch (IOException e) {
            logger.warn(e);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent
            .getServletContext()
            .setInitParameter(Log4jWebSupport.LOG4J_CONFIG_LOCATION, log4j2ConfigFile);
        listener.contextInitialized(servletContextEvent);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        listener.contextDestroyed(servletContextEvent);
    }

    private void getEnvironmentProperties() throws IOException {
        Properties props = this.getProperties();
        this.log4j2ConfigFile = "log4j2.xml";
        String log4jInternalFile =
            String.format("%s/webapps/%s", System.getProperty("catalina.base"), log4j2ConfigFile);
        File f = new File(log4jInternalFile);
        if (f.exists() && ! f.isDirectory()) {
            this.log4j2ConfigFile = log4jInternalFile;
        }
        String log4JExternalFile = props.getProperty("fr.insee.rmes.api.log.configuration");
        File f2 = new File(log4JExternalFile);
        if (f2.exists() && ! f2.isDirectory()) {
            this.log4j2ConfigFile = String.format(log4JExternalFile);
        }
    }

    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("rmes-api.properties"));
        File f = new File(String.format("%s/webapps/%s", System.getProperty("catalina.base"), "rmes-api.properties"));
        if (f.exists() && ! f.isDirectory()) {
            FileReader r = new FileReader(f);
            props.load(r);
            r.close();
        }
        return props;
    }

}
