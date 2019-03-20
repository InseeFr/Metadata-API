package fr.insee.rmes.api.operations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Operation {
	
	private String id = null;
	private String labelLg1 = null;
	private String labelLg2 = null;
	
	@JsonInclude(Include.NON_NULL)
	private String simsId = null;
	
	public Operation(String id, String labelLg1, String labelLg2, String simsId) {
		super();
		this.id = id;
		this.labelLg1 = labelLg1;
		this.labelLg2 = labelLg2;
		this.simsId = simsId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelLg1() {
		return labelLg1;
	}

	public void setLabelLg1(String labelLg1) {
		this.labelLg1 = labelLg1;
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
	
	

}