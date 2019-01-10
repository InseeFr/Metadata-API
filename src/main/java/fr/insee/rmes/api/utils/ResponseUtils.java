package fr.insee.rmes.api.utils;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ResponseUtils {
	
	private static Logger logger = LogManager.getLogger(ResponseUtils.class);
		
	public static String produceResponse(Object obj, String header) {
		ObjectMapper mapper = new ObjectMapper();;
		String response = "";
		if (header != null && header.equals(MediaType.APPLICATION_XML)) {
			mapper = new XmlMapper();
		}
		else {
			mapper = new ObjectMapper();
		} 
		try {
			response = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return response;
	}

}
