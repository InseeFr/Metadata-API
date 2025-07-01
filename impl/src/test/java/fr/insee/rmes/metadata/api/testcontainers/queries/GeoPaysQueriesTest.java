package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoDepartementEndpoints;
import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Tag("integration")
class GeoPaysQueriesTest  extends TestcontainerTest{

    @Autowired
    GeoDepartementEndpoints endpoints;

    @Test
    void should_return_all_datasets_based_on_stamp() {
        var d  = endpoints.getcogdep("75", LocalDate.now());
        Assert.assertEquals("Paris", d.getBody().getIntitule());
    }
}

