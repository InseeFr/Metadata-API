package fr.insee.rmes.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoading {

    public Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("rmes-api.properties"));
        props = this.loadIfExists(props, "rmes-api.properties");
        props = this.loadIfExists(props, "rmeswnci.properties");
        props = this.loadIfExists(props, "rmeswncz.properties");
        props = this.loadIfExists(props, "rmeswncd.properties");

        return props;
    }

    /*
     * load properties on catalina base
     */
    public Properties loadIfExists(Properties props, String filename) throws IOException {
        File f;
        f = new File(String.format("%s/webapps/%s", System.getProperty("catalina.base"), filename));
        if (f.exists() && ! f.isDirectory()) {
            FileReader r = new FileReader(f);
            props.load(r);
            r.close();
        }
        return props;
    }

}
