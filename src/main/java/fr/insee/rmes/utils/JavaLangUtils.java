package fr.insee.rmes.utils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class JavaLangUtils {

    private JavaLangUtils(){}

    public static <S,T, R> Stream<R> merge(Stream<S> stream, List<T> list, BiFunction<S,T,R> merger) {
        return stream.flatMap(s->list.stream().map(t->merger.apply(s,t)));
    }

}
