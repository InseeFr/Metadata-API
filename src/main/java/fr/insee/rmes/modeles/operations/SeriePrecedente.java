package fr.insee.rmes.modeles.operations;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name="SeriePrecedente")
@Schema(name = "SeriePrecedente", description = "Série liée")
public class SeriePrecedente extends Serie {

}
