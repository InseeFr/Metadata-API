package fr.insee.rmes.metadata.requestprocessor;

import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;
import fr.insee.rmes.metadata.queryexecutor.ParametersForQuery;
import fr.insee.rmes.metadata.utils.DateUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;

public record DescendantsRequestParametizer(String code, LocalDate date,
                                            TypeEnumInclusDansDepartement type,
                                            String filtreNom,
                                            boolean ascendant) implements ParametersForQuery {
    public DescendantsRequestParametizer(String code, LocalDate date, TypeEnumInclusDansDepartement type, String filtreNomDescendant) {
        this(code, date, type, filtreNomDescendant, false);
    }

    @Override
    public Object decodeValueForField(Object rawValue, String fieldName) {
        if ("type".equals(fieldName)){
            return rawValue==null?"none":((TypeEnumInclusDansDepartement)rawValue).getValue();
        }
        return ParametersForQuery.super.decodeValueForField(rawValue, fieldName);
    }

    @Override
    public void addFieldsToParameters(Map<String, Object> parameters, Field field) {
        switch (field.getName()){
            case "type" -> parameters.put("type", type ==null?"none": type.getValue());
            case "filtreNom" -> parameters.put("filtreNom", filtreNom ==null?"*": filtreNom);
            case "date" -> parameters.put("date", date==null? DateUtils.getDateTodayStringFormat() :date.toString());
            case "code" -> parameters.put("code", code);
            case "ascendant" -> parameters.put("ascendant", ascendant?"true":"false");
        }
    }
}
