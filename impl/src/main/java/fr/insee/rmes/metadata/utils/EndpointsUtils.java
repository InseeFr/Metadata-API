package fr.insee.rmes.metadata.utils;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class EndpointsUtils {

    private EndpointsUtils() {}

    public static <E> ResponseEntity<List<E>> toResponseEntity(List<E> result) {
        return result == null || result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

}
