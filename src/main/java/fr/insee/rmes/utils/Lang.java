package fr.insee.rmes.utils;

public enum Lang {
    FR("fr"), EN("en");

    private String language;

    private Lang(String language) {
        this.language = language;
    }

    public String getLang() {
        return language;
    }

}
