package fr.insee.rmes.modeles.utils;

public class FiltreNom {
    private String filtreNom;

    public FiltreNom(String filtreNom) {
        if (filtreNom == null){
            this.filtreNom = null;
        }
        else{
            this.filtreNom = filtreNom;
        }
    }

    public String getString() {
        if (filtreNom != null && !filtreNom.isEmpty()) {
            return filtreNom;
        }
        else{
            return null;                //without this it might cause some trouble to test with new FiltreNom(null)
        }
    }
}
