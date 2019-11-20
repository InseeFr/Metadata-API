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

    private static void csvToPOJO(String csv, Object pojo) throws Exception {
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
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private static <TargetClass> List<TargetClass> csvToMultiPOJO(String csv, Class<TargetClass> childClass) throws Exception {
    	List<TargetClass> list = new ArrayList<TargetClass>();
    	CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(childClass).withHeader();
        MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class).with(schema).readValues(csv);
        while (it.hasNext()) {
            Map<String, String> rowAsMap = it.next();
    		TargetClass activite = mapper.convertValue(rowAsMap, childClass);
    		list.add(activite);
        }
        return list;
    }

    public static <TargetClass>  List<TargetClass> populateMultiPOJO(String csv, Class<TargetClass> childClass) {
    	List<TargetClass> list = new ArrayList<TargetClass>();
        try {
            list = csvToMultiPOJO(csv, childClass);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

}
