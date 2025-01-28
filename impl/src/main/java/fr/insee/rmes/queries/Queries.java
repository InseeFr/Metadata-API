package fr.insee.rmes.queries;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.config.FreemarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class Queries {
	
    private static Logger logger = LogManager.getLogger(Queries.class);

    public static String buildRequest(String root, String fileName, Map<String, Object> params)  {
        Template temp;
        Writer out = new StringWriter();
        try {
            temp = FreemarkerConfig.getCfg().getTemplate(root + fileName);
            temp.process(params, out);
        }
        catch (IOException | TemplateException e) {
            logger.error("Can't read query {}", fileName);
        }
        return out.toString();
    }
    

}
