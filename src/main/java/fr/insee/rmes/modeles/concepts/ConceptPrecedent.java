package fr.insee.rmes.modeles.concepts;

import java.util.Objects;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name="ConceptPrecedent")
@Schema(name = "ConceptPrecedent", description = "concept lié")
public class ConceptPrecedent {
	@Schema(example = "c1501")
	protected String id = null;
    @Schema(example = "http://id.insee.fr/concepts/definition/c1501")
    protected String uri = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConceptPrecedent(String id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public ConceptPrecedent() {
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
		ConceptPrecedent other = (ConceptPrecedent) obj;
		return Objects.equals(id, other.id) && Objects.equals(uri, other.uri);
	}

}
