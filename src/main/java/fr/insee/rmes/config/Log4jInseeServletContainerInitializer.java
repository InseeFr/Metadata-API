package fr.insee.rmes.config;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.web.Log4jServletContainerInitializer;
import org.apache.logging.log4j.web.Log4jWebSupport;
import fr.insee.rmes.utils.PropertiesUtils;


public class Log4jInseeServletContainerInitializer extends Log4jServletContainerInitializer {

	private static final String WEBAPPS = "%s/webapps/%s";

	private static final String CATALINA_BASE = "catalina.base";

	@Override
	public void onStartup(final Set<Class<?>> classes, final ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter(Log4jWebSupport.LOG4J_CONFIG_LOCATION, findLog4jConfFile());
		super.onStartup(classes, servletContext);
		switchOffLog4jServletContainerInitializer(servletContext);
	}

	private boolean switchOffLog4jServletContainerInitializer(ServletContext servletContext) {
		return servletContext.setInitParameter(Log4jWebSupport.IS_LOG4J_AUTO_INITIALIZATION_DISABLED, "true");
	}

	private String findLog4jConfFile() {

		Path devPropsInClassPath;
		try {
			devPropsInClassPath = Paths.get(getClass().getClassLoader().getResource("rmes-api.properties").toURI());
		} catch (URISyntaxException e) {
			devPropsInClassPath = null;
		}

		return Stream.of(Paths.get(String.format(WEBAPPS, System.getProperty(CATALINA_BASE), "production.properties")),
						Paths.get(String.format(WEBAPPS, System.getProperty(CATALINA_BASE), "rmes-api.properties")),
						Paths.get(String.format(WEBAPPS, System.getProperty(CATALINA_BASE), "rmeswnci.properties")),
						Paths.get(String.format(WEBAPPS, System.getProperty(CATALINA_BASE), "rmeswncz.properties")),
						Paths.get(String.format(WEBAPPS, System.getProperty(CATALINA_BASE), "rmeswncd.properties")),
						devPropsInClassPath)
				.filter(Objects::nonNull)
				.map(p -> PropertiesUtils.readPropertyFromPath("fr.insee.rmes.api.log.configuration", p))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.findFirst()
				.orElse("log4j2.xml");
	}
}
