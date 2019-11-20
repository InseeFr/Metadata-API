package fr.insee.rmes.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StringWithLang {
	@JsonProperty("contenu")
	private String string = null;
	 @JsonProperty("langue") 
	private Lang lang = null;
    
	public String getString() {
		return string;
	}
	
	public StringWithLang(String string, Lang lang) {
		super();
		this.string = string;
		this.lang = lang;
	}	
	
	public void setString(String string) {
		this.string = string;
	}
	public String getLang() {
		return lang.getLang();
	}
	public void setLang(Lang lang) {
		this.lang = lang;
	}
	
	

}
