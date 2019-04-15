package fr.insee.rmes.api.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class StringWithLang {
	
    @JacksonXmlText
	@JsonProperty("contenu")
	private String string = null;
    @JacksonXmlProperty(isAttribute = true)
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
