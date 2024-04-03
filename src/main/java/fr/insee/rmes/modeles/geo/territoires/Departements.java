package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.Departement;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Departements")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Departements", description = "Tableau représentant les départements")
public class Departements extends Territoires {

        private List<Departement> departements = null;

        public Departements() {}

        public Departements(List<Departement> departements) {
            this.departements = departements;
        }

        @JacksonXmlProperty(isAttribute = true, localName = "Departement")
        @JacksonXmlElementWrapper(useWrapping = false)
        public List<Departement> getDepartements() {
            return departements;
        }

        public void setDepartementss(List<Departement> departements) {
            this.departements = departements;
        }
}

    

