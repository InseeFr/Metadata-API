package fr.insee.rmes.modeles.operations;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name="IndicateurSuivant")
@Schema(name = "IndicateurSuivant", description = "Indicateur suivant")
public class IndicateurSuivant extends Indicateur{


}