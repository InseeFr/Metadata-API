package fr.insee.rmes.api;

import org.springframework.beans.factory.annotation.Autowired;

import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.SparqlUtils;

public abstract class MetadataApi {

    @Autowired
    protected SparqlUtils sparqlUtils;

    @Autowired
    protected CSVUtils csvUtils;

    public SparqlUtils getSparqlUtils() {
        return sparqlUtils;
    }

    public void setSparqlUtils(SparqlUtils sparqlUtils) {
        this.sparqlUtils = sparqlUtils;
    }

    public CSVUtils getCsvUtils() {
        return csvUtils;
    }

    public void setCsvUtils(CSVUtils csvUtils) {
        this.csvUtils = csvUtils;
    }

}
