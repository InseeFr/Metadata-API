package fr.insee.rmes.modeles.geo.territoire;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

public class CsvProjection {
    
    private static Logger logger = LogManager.getLogger(CsvProjection.class);


    protected String type;

    protected String codeOrigine = null;
    protected String origine = null;
    protected String intituleOrigine = null;
    protected String dateCreationOrigine = null;
    protected String dateSuppressionOrigine = null;
    protected IntituleSansArticle intituleSansArticleOrigine;
    protected String chefLieuOrigine = null;

    protected String code = null;
    protected String uri = null;
    protected String intitule = null;
    protected String dateCreation = null;
    protected String dateSuppression = null;
    protected IntituleSansArticle intituleSansArticle;
    protected String chefLieu = null;
    protected String intituleComplet = null;

    public CsvProjection() {
        this.intituleSansArticle = new IntituleSansArticle();
        this.intituleSansArticleOrigine = new IntituleSansArticle();
    }

    public String getOrigine() {
        return origine;
    }

    public Territoire getTerritoireOrigine() {
        Class<? extends Territoire> territoireClass = EnumTypeGeographie.getClassByType(type);
        Territoire t = null;
        try {
            t =
                territoireClass
                    .getDeclaredConstructor(
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        IntituleSansArticle.class,
                        String.class)
                    .newInstance(
                        codeOrigine,
                        origine,
                        intituleOrigine,
                        type,
                        dateCreationOrigine,
                        dateSuppressionOrigine,
                        intituleSansArticleOrigine,
                        chefLieuOrigine);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            logger.error("Error in getTerritoireOrigine - {}",e.getMessage());
        }
        return t;
    }

    public Territoire getTerritoireProjete() {
        Class<? extends Territoire> territoireClass = EnumTypeGeographie.getClassByType(type);
        Territoire t = null;
        try {
            t =
                territoireClass
                    .getDeclaredConstructor(
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        IntituleSansArticle.class,
                        String.class)
                    .newInstance(
                        code,
                        uri,
                        intitule,
                        type,
                        dateCreation,
                        dateSuppression,
                        intituleSansArticle,
                        chefLieu);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            logger.error("Error in getTerritoireProjete - {}",e.getMessage());
        }
        return t;
    }

    public void setCodeOrigine(String codeOrigine) {
        this.codeOrigine = codeOrigine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public void setIntituleOrigine(String intituleOrigine) {
        this.intituleOrigine = intituleOrigine;
    }

    public void setDateCreationOrigine(String dateCreationOrigine) {
        this.dateCreationOrigine = dateCreationOrigine;
    }

    public void setDateSuppressionOrigine(String dateSuppressionOrigine) {
        this.dateSuppressionOrigine = dateSuppressionOrigine;
    }

    public void setIntituleSansArticleOrigine(String intituleSansArticleOrigine) {
        this.intituleSansArticleOrigine.setIntituleSansArticle(intituleSansArticleOrigine);
    }

    public void setTypeArticleOrigine(String typeArticleOrigine) {
        this.intituleSansArticleOrigine.setTypeArticle(typeArticleOrigine);
    }

    public void setChefLieuOrigine(String chefLieuOrigine) {
        this.chefLieuOrigine = chefLieuOrigine;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateSuppression(String dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    public void setIntituleSansArticle(String intituleSansArticle) {
        this.intituleSansArticle.setIntituleSansArticle(intituleSansArticle);
    }

    public void setTypeArticle(String typeArticle) {
        this.intituleSansArticle.setTypeArticle(typeArticle);
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

    public void setIntituleComplet(String intituleComplet) {
        this.intituleComplet = intituleComplet;
    }
    public String getIntituleComplet() {
        return intituleComplet;
    }
}
