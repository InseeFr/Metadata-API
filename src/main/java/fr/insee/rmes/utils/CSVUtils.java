package fr.insee.rmes.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.Territoire;

public class CSVUtils {

    private static Logger logger = LogManager.getLogger(CSVUtils.class);

    private Object csvToPOJO(String csv, Object pojo) throws IOException {
        CsvMapper mapper = new CsvMapper();
        // emptyScheme is necessary
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

        MappingIterator<Object> it = mapper.readerForUpdating(pojo).with(bootstrapSchema).readValues(csv);
        while (it.hasNextValue()) {
            pojo = it.nextValue();
        }
        return pojo;
    }

    /*
     * Create POJO with the result of a request
     */
    public Object populatePOJO(String csv, Object pojo) {
        try {
            pojo = this.csvToPOJO(csv, pojo);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return pojo;
    }

    private <T> List<T> csvToMultiPOJO(String csv, Class<T> childClass) throws IOException {
        List<T> list = new ArrayList<>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class).with(schema).readValues(csv);
        while (it.hasNext()) {
            Map<String, String> rowAsMap = it.next();
            if (childClass == Territoire.class) {
                String valueOfTypeMap = rowAsMap.get("type");
                String nomType = StringUtils.substringAfterLast(valueOfTypeMap, "#");
                Class<?> childClassBis =  EnumTypeGeographie.getClassByType(nomType);
                childClass = (Class<T>) childClassBis;
            }
            T activite = mapper.convertValue(rowAsMap, childClass);
            list.add(activite);
        }
        return list;
    }

    /**
     * Create POJOs
     * @param csv : result of the request
     * @param childClass : POJO class
     * @return
     */
    public <T> List<T> populateMultiPOJO(String csv, Class<T> childClass) {
        List<T> list = new ArrayList<>();
        try {
            list = this.csvToMultiPOJO(csv, childClass);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

}
