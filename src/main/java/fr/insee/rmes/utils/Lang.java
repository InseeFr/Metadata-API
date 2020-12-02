package fr.insee.rmes.utils;

import fr.insee.rmes.config.Configuration;

public enum Lang {
    FR("fr", Configuration.getBaseHost() + "/codes/langue/fr"), 
    EN("en", Configuration.getBaseHost() + "/codes/langue/en");

    private String language;
    private String uri;

    private Lang(String language, String uri) {
        this.language = language;
        this.uri = uri;
    }

    public String getLang() {
        return language;
    }

	public String getUri() {
		return uri;
	}

}

