package fr.insee.rmes.metadata.api.testcontainers.queries;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

public class TestcontainerTest {
    static GraphDBContainer container = new GraphDBContainer("ontotext/graphdb:10.8.8");

    @BeforeAll
    static void startContainer(){
        container.start();
    }

    @AfterAll
    static void stopContainer(){
        container.stop();
    }


    @DynamicPropertySource
    static void overrideSpringProperties(DynamicPropertyRegistry registry) {
        registry.add("fr.insee.rmes.metadata.api.sparqlEndpoint", () -> "http://" + container.getHost() + ":" + container.getMappedPort(7200)+ "/repositories/data") ;
    }
}
