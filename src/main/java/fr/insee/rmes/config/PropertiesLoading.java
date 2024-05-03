package fr.insee.rmes.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoading {

	public Properties getProperties() throws IOException {
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("api-stable.properties"));
		props.load(this.getClass().getClassLoader().getResourceAsStream("rmes-api.properties"));
		this.loadIfExists(props, "rmes-api.properties");
		this.loadIfExists(props, "rmeswnci.properties");
		this.loadIfExists(props, "rmeswncz.properties");
		this.loadIfExists(props, "rmeswncd.properties");
		this.loadIfExists(props, "production.properties");
		this.loadFromConfigMap(props, "/conf/rmes-api.properties");
		return props;
	}

	/*
	 * load properties on catalina base
	 */
	private Properties loadIfExists(Properties props, String filename) throws IOException {
		File f;
		f = new File(String.format("%s/webapps/%s", System.getProperty("catalina.base"), filename));
		if (f.exists() && !f.isDirectory()) {
			try (FileReader r = new FileReader(f);){ 
				props.load(r);
				return props;
			}
		}
		return props;
	}

	/*
	 * Load properties from ConfigMap mounted path and return the modified properties
	 */
	private Properties loadFromConfigMap(Properties props, String path) throws IOException {
		File file = new File(path);
		if (file.exists() && !file.isDirectory()) {
			try (FileReader reader = new FileReader(file)) {
				props.load(reader);
			}
		}
		return props;
	}

}
