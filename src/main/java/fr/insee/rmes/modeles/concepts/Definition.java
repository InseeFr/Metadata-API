package fr.insee.rmes.modeles.concepts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
    @XmlElement(name = "Remplace")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<SimpleObject> remplace = new ArrayList<>();

    @JsonInclude(Include.NON_EMPTY)
    @XmlElement(name = "EstRemplacePar")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<SimpleObject> estRemplacePar =  new ArrayList<>();
    
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


    @JacksonXmlProperty(localName = "Remplace")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SimpleObject> getRemplace() {
        return remplace;
    }

    @JacksonXmlProperty(localName = "EstRemplacePar")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SimpleObject> getEstRemplacePar() {
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
			SimpleObject so = new SimpleObject(link.getId(), link.getUri());
			if (StringUtils.equals("replaces",link.getTypeOfLink())) {
				remplace.add(so);
			}else if (StringUtils.equals("isReplacedBy",link.getTypeOfLink())) {
				estRemplacePar.add(so);
			}
		});		
	}

}
