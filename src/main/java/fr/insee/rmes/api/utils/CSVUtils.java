package fr.insee.rmes.api.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVUtils {

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
			e.printStackTrace();
		}
	}

}
