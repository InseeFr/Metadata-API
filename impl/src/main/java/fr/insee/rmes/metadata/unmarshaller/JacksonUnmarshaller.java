package fr.insee.rmes.metadata.unmarshaller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.queryexecutor.Csv;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@Slf4j
// TODO proposer une autre implémentation d'unmarshaller ?
public record JacksonUnmarshaller(CsvMapper csvMapper) implements Unmarshaller {

    public JacksonUnmarshaller() {
        this(CsvMapper.csvBuilder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .addModule(articleEnumModule())
                .addModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build());
    }

    private static Module articleEnumModule() {
        var module = new SimpleModule();
        module.addDeserializer(TerritoireTousAttributs.TypeArticleEnum.class, new JsonDeserializer<>() {
            @Override
            public TerritoireTousAttributs.TypeArticleEnum deserialize(JsonParser parser, DeserializationContext ctxt) {
                try {
                    return TerritoireTousAttributs.TypeArticleEnum.values()[Integer.parseInt(parser.getValueAsString())];
                } catch (NumberFormatException | IOException e) {
                    return TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_;
                }
            }
        });
        return module;
    }

    @Override
    public <G> Optional<G> unmarshal(@NonNull Csv csv, @NonNull Class<G> targetClass) {
        return unmarshallAll(csv.content(), targetClass, Optional.empty(), l-> l.isEmpty()?Optional.empty():Optional.of(l.getFirst()));
    }

    @Override
    public <G> List<G> unmarshalList(@NonNull Csv csv, @NonNull Class<G> targetClass) {
        return unmarshallAll(csv.content(), targetClass, List.of(), Function.identity());
    }

    private <R, G> R unmarshallAll(String csv, Class<G> targetClass, R resultEmpty, Function<List<G>, R> extractResults){
        log.atDebug().log(() -> "Deserialize for "+findReturned(targetClass, resultEmpty)
                        +". CSV header is "+ csv.lines().limit(1).findFirst().orElse(null));
        CsvSchema schema = CsvSchema.emptySchema()
                .withHeader()
                .withNullValue("");
        ObjectReader reader = csvMapper.readerFor(targetClass).with(schema);
        List<G> results;
        try (MappingIterator<G> mappingIterator = reader.readValues(csv)) {
            results = mappingIterator.readAll();
        } catch (IOException e) {
            log.error("While reading \n{}\nMESSAGE : {}\n===> RETURN WILL BE EMPTY", csv, e.getMessage());
            return resultEmpty;
        }
        return extractResults.apply(results);
    }

    private <G, R> String findReturned(Class<G> targetClass, R resultEmpty) {
        return switch (resultEmpty){
            case List<?> ignored -> "List<"+targetClass.getName()+">";
            case Optional<?> ignored -> targetClass.getName();
            default -> "Unknown wrapper for "+targetClass.getName();
        };
    }
}
