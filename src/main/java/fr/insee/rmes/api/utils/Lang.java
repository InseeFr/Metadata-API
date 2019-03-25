package fr.insee.rmes.api.utils;

public enum Lang {
	FR("fr"), EN("en");
	
	private String lang;

	private Lang(String lang) {
		this.setLang(lang);
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
}
