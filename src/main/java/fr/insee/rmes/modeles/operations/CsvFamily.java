package fr.insee.rmes.modeles.operations;

public class CsvFamily {

    private String id = null;
    private String labelLg1 = null;
    private String labelLg2 = null;
    private String altlabelLg1 = null;
    private String altlabelLg2 = null;
    private String uri = null;

    public CsvFamily() {// No-args constructor needed for JAXB
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

	public String getAltlabelLg1() {
		return altlabelLg1;
	}

	public void setAltlabelLg1(String altlabelLg1) {
		this.altlabelLg1 = altlabelLg1;
	}

	public String getAltlabelLg2() {
		return altlabelLg2;
	}

	public void setAltlabelLg2(String altlabelLg2) {
		this.altlabelLg2 = altlabelLg2;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
    


}
