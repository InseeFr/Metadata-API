package fr.insee.rmes.utils;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import fr.insee.rmes.modeles.StringWithLang;
import fr.insee.rmes.modeles.StringXmlMixIn;
import fr.insee.rmes.modeles.geo.Commune;
import fr.insee.rmes.modeles.geo.Departement;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import fr.insee.rmes.modeles.geo.IntituleSansArticleXmlMixIn;
import fr.insee.rmes.modeles.geo.Region;
import fr.insee.rmes.modeles.geo.ZoneGeoJsonMixIn;

public class ResponseUtils {

    private static Logger logger = LogManager.getLogger(ResponseUtils.class);

    public String produceResponse(Object obj, String header) {
        ObjectMapper mapper;
        String response = "";
        if (header != null && header.equals(MediaType.APPLICATION_XML)) {
            mapper = new XmlMapper();
            mapper.addMixIn(StringWithLang.class, StringXmlMixIn.class);
            mapper.addMixIn(IntituleSansArticle.class, IntituleSansArticleXmlMixIn.class);
        }
        else {
            mapper = new ObjectMapper();
            mapper.addMixIn(Commune.class, ZoneGeoJsonMixIn.class);
            mapper.addMixIn(Region.class, ZoneGeoJsonMixIn.class);
            mapper.addMixIn(Departement.class, ZoneGeoJsonMixIn.class);
        }
        try {
            response = mapper.writeValueAsString(obj);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response;
    }

}
