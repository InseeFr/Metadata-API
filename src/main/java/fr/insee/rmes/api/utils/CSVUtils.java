package fr.insee.rmes.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVUtils {
	
	private static Logger logger = LogManager.getLogger(CSVUtils.class);

	public static void csvToPOJO(String csv, Object pojo) throws Exception {
		CsvMapper mapper = new CsvMapper();
		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

		MappingIterator<Object> it = mapper.readerForUpdating(pojo).with(bootstrapSchema).readValues(csv);
		while (it.hasNextValue()) {
			pojo = it.nextValue();
		}
	}
	
	public static void populatePOJO(String csv, Object pojo) {
		try {
			csvToPOJO(csv, pojo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Object> csvToMultiPOJO(String csv, Class childClass) throws Exception {
		List<Object> list = new ArrayList<Object>();
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader(); 
		MappingIterator<Map<String,String>> it = mapper.readerFor(Map.class)
		   .with(schema)
		   .readValues(csv);
		while (it.hasNext()) {
		  Map<String,String> rowAsMap = it.next();
		  Object activite = mapper.convertValue(rowAsMap, childClass);
		  list.add(activite);
		}
		return list;		
	}
	
	public static List<?> populateMultiPOJO(String csv, @SuppressWarnings("rawtypes") Class childClass) {
		List<?> list = new ArrayList<>();
		try {
			list = csvToMultiPOJO(csv, childClass);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

}
