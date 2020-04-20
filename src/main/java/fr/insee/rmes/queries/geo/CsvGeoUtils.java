package fr.insee.rmes.queries.geo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import fr.insee.rmes.modeles.geo.territoire.CsvProjection;
import fr.insee.rmes.modeles.geo.territoire.Projection;
import fr.insee.rmes.modeles.geo.territoire.Territoire;

public class CsvGeoUtils {
    
    private static Logger logger = LogManager.getLogger(CsvGeoUtils.class);


    /**
     * Create complex POJOs (containing list)
     * @param csv : result of the request
     * @param childClass : POJO class
     * @return
     */
    public <T extends Territoire> List<Projection> populateProjections(String csv) {
        Map<String,Projection> alreadyCreated = new HashMap<String, Projection>();

        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class).with(schema).readValues(csv);
            while (it.hasNext()) {
                Map<String, String> rowAsMap = it.next();
                
                CsvProjection pojo =  mapper.convertValue(rowAsMap, CsvProjection.class);
                if (alreadyCreated.containsKey(pojo.getOrigine())){                    
                    alreadyCreated.get(pojo.getOrigine()).addProjete(pojo.getTerritoireProjete());
                }else {
                    Projection p = new Projection(pojo.getTerritoireOrigine(), pojo.getTerritoireProjete());
                    alreadyCreated.put(pojo.getOrigine(), p);
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return alreadyCreated.values().stream().collect(Collectors.toList());
    }


}
