package fr.insee.rmes.modeles.operations;

import org.apache.commons.lang3.StringUtils;

import fr.insee.rmes.modeles.StringWithLang;
import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ObjetSimpleAvecSims", description = "Objet simple avec documentation SIMS")
public class ObjectWithSimsId extends SimpleObject {

    private String simsId = null;
  
    public ObjectWithSimsId(String id, String uri, String labelFr, String labelEn, String simsId) {
        this.id = id;
        this.uri = uri;
        if (StringUtils.isNotEmpty(labelFr)) {
            label.add(new StringWithLang(labelFr, Lang.FR));
        }
        if (StringUtils.isNotEmpty(labelEn)) {
            label.add(new StringWithLang(labelEn, Lang.EN));
        }
        this.simsId=simsId;
    }

	public ObjectWithSimsId() {
		super();
	}

	public String getSimsId() {
		return simsId;
	}

	public void setSimsId(String simsId) {
		this.simsId = simsId;
	}


}
