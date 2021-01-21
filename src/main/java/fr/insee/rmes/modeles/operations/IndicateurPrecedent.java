package fr.insee.rmes.modeles.operations;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name="IndicateurPrecedent")
@Schema(name = "IndicateurPrecedent", description = "Indicateur précédent")
public class IndicateurPrecedent extends Indicateur{


}