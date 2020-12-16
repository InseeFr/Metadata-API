package fr.insee.rmes.modeles.concepts;

import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name="ConceptSuivant")
@Schema(name = "ConceptSuivant", description = "concept lié")
public class ConceptSuivant {
	@Schema(example = "c1500")
	protected String id = null;
    @Schema(example = "http://id.insee.fr/concepts/definition/c1500")
    protected String uri = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConceptSuivant(String id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public ConceptSuivant() {
        super();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

  
	@Override
	public int hashCode() {
		return Objects.hash(id, uri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConceptSuivant other = (ConceptSuivant) obj;
		return Objects.equals(id, other.id) && Objects.equals(uri, other.uri);
	}

}
