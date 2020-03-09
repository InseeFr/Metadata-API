package fr.insee.rmes.modeles.geo.territoire;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoires.Territoires;

public class Projection {
    
    private static Logger logger = LogManager.getLogger(Projection.class);
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    Territoire origine ;
    
    @JsonIgnore
    List<Territoire> projetes =  new ArrayList<>();
    
    @JacksonXmlElementWrapper(useWrapping = false)
   @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
   Territoires listeProj;
    
    public Projection() {
    }
    
    public Projection(Territoire origine, Territoire t) {
        this.origine = origine;
        addProjete(t);
    }
  
    public Territoire getOrigine() {
        return origine;
    }
    public void setOrigine(Territoire origine) {
        this.origine = origine;
    }
   
    public List<Territoire> getProjetes() {
        return projetes;
    }
    public void setProjetes(List<Territoire> projetes) {
        this.projetes = projetes;
    }
    
    @JsonIgnore
    public String getIdentifiant() {
        return origine.getUri();
    }
    
    public void addProjete(Territoire t) {
        projetes.add(t);
    }

    public Territoires getListeProj() {
        Class<? extends Territoires> classeTerritoire = EnumTypeGeographie.getPluralClassByType(origine.getType());
        try {
            return classeTerritoire.getDeclaredConstructor(List.class).newInstance(projetes);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public void setListeProj(Territoires listeProj) {
        this.listeProj = listeProj;
    }    
    
}
