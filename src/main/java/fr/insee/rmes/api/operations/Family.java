package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Family {
	
	private String id = null;
	private String labelLg1 = null;
	private String labelLg2 = null;
	
	@JsonInclude(Include.NON_NULL)
	private List<Serie> series;
	
	public Family(String id, String labelLg1,String labelLg2, Serie serie) {
		if (serie != null) {
			setSeries(new ArrayList<Serie>());
			series.add(serie);
		}
		this.id = id;
		this.labelLg1 = labelLg1;
		this.labelLg2 = labelLg2;
	}

	
	@JacksonXmlProperty(isAttribute=true, localName="Serie")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}	
	
	public void addSerie(Serie serie) {
		if (series==null) {
			setSeries(new ArrayList<Serie>());
		}
		this.series.add(serie);
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


}
