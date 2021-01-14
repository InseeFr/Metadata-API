package fr.insee.rmes.modeles.operations;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name="SerieSuivante")
@Schema(name = "SerieSuivante", description = "Série liée")
public class SerieSuivante extends Serie {

}
