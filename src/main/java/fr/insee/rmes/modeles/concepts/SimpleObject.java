package fr.insee.rmes.modeles.concepts;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Concept", description = "concept lié")
public class SimpleObject {

	protected String id = null;
    @Schema(example = "http://id.insee.fr/...")
    protected String uri = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SimpleObject(String id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public SimpleObject() {
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
		SimpleObject other = (SimpleObject) obj;
		return Objects.equals(id, other.id) && Objects.equals(uri, other.uri);
	}

}
