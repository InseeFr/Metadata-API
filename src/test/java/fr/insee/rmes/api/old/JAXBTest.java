package fr.insee.rmes.api.old;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.insee.rmes.modeles.geo.territoire.Region;

// TODO needs to be transformed into fonctional test

@Ignore
public class JAXBTest {

    @Test
    public void testJSON() throws JAXBException {

        Region region = new Region();
        region.setCode("27");
        region.setIntitule("Bourgogne-Franche-Comté");
        region.setUri("http://id.insee.fr/geo/region/27");

        // For some reason, jaxb.properties seems ignored, so creating explicitly a MOXy context
        JAXBContext context = JAXBContextFactory.createContext(new Class[] {
            Region.class
        }, null);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("eclipselink.media-type", "application/xml");
        System.out.println("XML:");
        marshaller.marshal(region, System.out);
        marshaller.setProperty("eclipselink.media-type", "application/json");
        marshaller.setProperty("eclipselink.json.include-root", false);
        System.out.println("\nJSON:");
        marshaller.marshal(region, System.out);
        Assertions.assertTrue(true);
    }
}
