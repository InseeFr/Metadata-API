package fr.insee.rmes.metadata.queryexecutor;

import java.io.Writer;

public record Query(String value) {
    public Query (Writer out){
        this(out.toString());
    }

}
