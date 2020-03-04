package fr.insee.rmes.modeles.geo.territoire;

import java.util.ArrayList;
import java.util.List;

public class Projection {
    
    Territoire origine ;
    List<Territoire> projetes =  new ArrayList<>();
    
    
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
    
    public String getIdentifiant() {
        return origine.getUri();
    }
    
    public void addProjete(Territoire t) {
        projetes.add(t);
    }    
    
}
