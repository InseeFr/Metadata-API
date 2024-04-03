package fr.insee.rmes.modeles.geo.territoire;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.xml.bind.annotation.XmlValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Inclusion {
    PARTIELLE, TOTALE;

    @JsonValue
    @XmlValue
    @Override
    public String toString(){
        return name().toLowerCase();
    }

}
