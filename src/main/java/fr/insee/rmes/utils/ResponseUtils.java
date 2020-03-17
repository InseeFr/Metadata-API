package fr.insee.rmes.utils;

import java.util.regex.Pattern;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import fr.insee.rmes.modeles.StringWithLang;
import fr.insee.rmes.modeles.StringXmlMixIn;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import fr.insee.rmes.modeles.geo.IntituleSansArticleXmlMixIn;
import fr.insee.rmes.modeles.geo.TerritoireJsonMixIn;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Projections;

public class ResponseUtils {

    private static Logger logger = LogManager.getLogger(ResponseUtils.class);

    public String produceResponse(Object obj, String header) {
        ObjectMapper mapper = null;
        String response = "";

        if (header != null && header.equals(MediaType.APPLICATION_XML)) {
            mapper = new XmlMapper();
            mapper.addMixIn(StringWithLang.class, StringXmlMixIn.class);
            mapper.addMixIn(IntituleSansArticle.class, IntituleSansArticleXmlMixIn.class);
        }
        else {

            mapper = new ObjectMapper();
            mapper.addMixIn(Territoire.class, TerritoireJsonMixIn.class);
        }

        try {
            response = mapper.writeValueAsString(obj);

            // Remove XML tag <listeTerritoires>
            response = Pattern.compile("<\\/?listeTerritoires>").matcher(response).replaceAll("");
            // Remove duplications Territoires objects with tag <territoire> for XML response
            response = Pattern.compile("(<territoires )(.+?)(<\\/territoires>)").matcher(response).replaceAll("");
            // Remove last tags territoires
            response = Pattern.compile("(<territoires><\\/territoires>)").matcher(response).replaceAll("");

            if ( ! response.isEmpty() && obj.getClass() == Projections.class) {

                if (header.equals(MediaType.APPLICATION_XML)) {
                    // Remove XML tag <origine>
                    response = Pattern.compile("<\\/?origine>").matcher(response).replaceAll("");
                    // Remove XML tag <listeProj>
                    response = Pattern.compile("<\\/?listeProj>").matcher(response).replaceAll("");
                }
                else {
                    // TODO json
                }
            }

        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        return response;
    }

}
