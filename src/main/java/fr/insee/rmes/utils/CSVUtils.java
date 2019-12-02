package fr.insee.rmes.utils;

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

    private Object csvToPOJO(String csv, Object pojo) throws Exception {
        CsvMapper mapper = new CsvMapper();
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

        MappingIterator<Object> it = mapper.readerForUpdating(pojo).with(bootstrapSchema).readValues(csv);
        while (it.hasNextValue()) {
            pojo = it.nextValue();
        }
        return pojo;
    }

    public Object populatePOJO(String csv, Object pojo) {
        try {
            pojo = this.csvToPOJO(csv, pojo);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return pojo;
    }

    private <TargetClass> List<TargetClass> csvToMultiPOJO(String csv, Class<TargetClass> childClass) throws Exception {
        List<TargetClass> list = new ArrayList<>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class).with(schema).readValues(csv);
        while (it.hasNext()) {
            Map<String, String> rowAsMap = it.next();
            TargetClass activite = mapper.convertValue(rowAsMap, childClass);
            list.add(activite);
        }
        return list;
    }

    public <TargetClass> List<TargetClass> populateMultiPOJO(String csv, Class<TargetClass> childClass) {
        List<TargetClass> list = new ArrayList<>();
        try {
            list = this.csvToMultiPOJO(csv, childClass);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

}
