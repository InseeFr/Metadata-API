package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Serie {

	private String id = null;
	private String labelLg1 = null;
	private String labelLg2 = null;
	
	@JsonInclude(Include.NON_NULL)
	private String simsId = null;
	
	@JsonInclude(Include.NON_NULL)
	private List<Operation> operations;
	@JsonInclude(Include.NON_NULL)
	private List<Indicateur> indicateurs;

	public Serie(String id, String labelLg1, String labelLg2) {	
		this.id=id;
		this.labelLg1 = labelLg1;
		this.labelLg2 = labelLg2;
	}
	
	public void addOperation(Operation op) {
		if (operations ==null) {
			setOperations(new ArrayList<Operation>());
		}
		this.operations.add(op);
	}

	public void addIndicateur(Indicateur indic) {
		if (indicateurs==null) {
				setIndicateurs(new ArrayList<Indicateur>());
		}
		this.indicateurs.add(indic);
	}


	public String getLabelLg1() {
		return labelLg1;
	}

	public void setLabelLg1(String seriesLabelLg1) {
		this.labelLg1 = seriesLabelLg1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelLg2() {
		return labelLg2;
	}

	public void setLabelLg2(String labelLg2) {
		this.labelLg2 = labelLg2;
	}

	public String getSimsId() {
		return simsId;
	}

	public void setSimsId(String simsId) {
		if (simsId != "") this.simsId = simsId;
	}

	@JacksonXmlProperty(isAttribute=true, localName="Operation")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@JacksonXmlProperty(isAttribute=true, localName="Indicateur")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Indicateur> getIndicateurs() {
		return indicateurs;
	}

	public void setIndicateurs(List<Indicateur> indicateurs) {
		this.indicateurs = indicateurs;
	}

}
