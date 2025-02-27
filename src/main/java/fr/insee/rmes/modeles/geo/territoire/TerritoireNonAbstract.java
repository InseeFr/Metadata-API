package fr.insee.rmes.modeles.geo.territoire;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@JsonTypeName("Territoire")
@JsonIgnoreProperties({ "intituleSansArticle","typeArticle"})
@JacksonXmlRootElement(localName = "Territoire")
@XmlRootElement(name = "Territoire")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un territoire")
public class TerritoireNonAbstract extends Territoire {

    @Schema(example = "Territoire")
    private String type = null;
    @Schema(example = "99100")
    private String code = null;
    @Schema(example ="http://id.insee.fr/geo/pays/b7e3f0c9-b653-4a3e-904a-de63b80e108b")
    private String uri = null;
    @Schema(example = "France")
    private String intitule = null;
    @Schema(example = "République française")
    @JsonProperty("intituleComplet")
    private String intituleComplet = null;
    @Schema(example = "1943-01-01")
    private String dateCreation = null;
    @Schema(example = "2025-12-12")
    private String dateSuppresion = null;


    // Constructeur par défaut requis pour la désérialisation
    public TerritoireNonAbstract() {
        super();
    }


    public TerritoireNonAbstract(String uri, String type, String code, String intituleComplet, String intitule, String dateCreation, String dateSuppression) {
        super(uri, type, code, intituleComplet, intitule, dateCreation, dateSuppression);
    }


}
