package fr.insee.rmes.api.utils;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ResponseUtils {
		
	public static String produceResponse(Object obj, String header) {
		ObjectMapper mapper;
		String response = "";
		if (header == null || header.equals(MediaType.APPLICATION_JSON)) {
			mapper = new ObjectMapper();
		}
		else {
			mapper = new XmlMapper();
		}
		try {
			response = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}

}
