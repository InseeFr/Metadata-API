package fr.insee.rmes.modeles.utils;

public class Date {

    private String date;

    public Date(String date) {
        if (date == null){
            this.date = null;
        }
        else{
            this.date = date;
        }
    }

    public String getString() {
        if (date != null && !date.isEmpty()) {
            return date;
        }
        else{
            return null;                //without this it might cause some trouble to test with new Date(null)
        }
    }
}
