package fr.insee.rmes.modeles.concepts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "IntituleDefinition")
@JacksonXmlRootElement(localName = "IntituleDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "IntituleDefinition", description = "Objet représentant la définition d'un concept statistique de l'Insee")
public class Definition {

	@XmlAttribute
    @Schema(example = "c2066")
    private String id = null;
	
	@XmlAttribute
    @Schema(example = "http://id.insee.fr/concepts/definition/c2066")
    private String uri = null;
    
    @XmlElement(name = "Intitule")
    @Schema(example = "Intitulé du concept à définir")
    private String intitule = null;
        
    @JsonInclude(Include.NON_EMPTY)
    private List<ConceptPrecedent> remplace = new ArrayList<>();

    @JsonInclude(Include.NON_EMPTY)
    private List<ConceptSuivant> estRemplacePar =  new ArrayList<>();
    
    private Boolean hasLink;
    

    public Definition() {}

    public Definition(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(localName = "Intitule")
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }


    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("conceptsPrecedents")
    @XmlElementWrapper(name = "ConceptsPrecedents")
    @JacksonXmlElementWrapper(localName = "ConceptsPrecedents")
    @JacksonXmlProperty(localName = "ConceptPrecedent")
    public List<ConceptPrecedent> getRemplace() {
        return remplace;
    }

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("conceptsSuivants") //json example
    @XmlElementWrapper(name = "ConceptsSuivants") //xml example list
    @JacksonXmlElementWrapper(localName = "ConceptsSuivants") //xml response
    @JacksonXmlProperty(localName = "ConceptSuivant") //xml response
    public List<ConceptSuivant> getEstRemplacePar() {
        return estRemplacePar;
    }

    @JsonIgnore
	public Boolean getHasLink() {
		return hasLink;
	}

    @JsonProperty(value = "hasLink")
	public void setHasLink(Boolean hasLink) {
		this.hasLink = hasLink;
	}

	public void setLinks(List<ConceptLink> links) {
		links.forEach(link -> {
			
			if (StringUtils.equals("replaces",link.getTypeOfLink())) {
				ConceptPrecedent so = new ConceptPrecedent(link.getId(), link.getUri());
				remplace.add(so);
			}else if (StringUtils.equals("isReplacedBy",link.getTypeOfLink())) {
				ConceptSuivant so = new ConceptSuivant(link.getId(), link.getUri());
				estRemplacePar.add(so);
			}
		});		
	}

}
