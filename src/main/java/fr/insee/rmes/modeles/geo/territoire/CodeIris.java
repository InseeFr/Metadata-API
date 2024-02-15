package fr.insee.rmes.modeles.geo.territoire;

import java.util.regex.Pattern;

import static fr.insee.rmes.api.geo.ConstGeoApi.PATTERN_IRIS;

public final class CodeIris {

    private static final Pattern IRIS_CODE_PATTERN = Pattern.compile(PATTERN_IRIS);
    public static final String PSEUDO_IRIS_SUFFIX = "0000";
    private final String code;
    private final boolean isInvalid;

    private CodeIris(String code) {
        this.code = code;
        isInvalid = ! IRIS_CODE_PATTERN.matcher(code).matches();
    }

    public static CodeIris of(String code) {
        return new CodeIris(code);
    }

    public boolean isInvalid() {
        return this.isInvalid;
    }

    public String code() {
        if (isInvalid){
            throw new IllegalStateException("Cannot get an invalid code (which does not match "+PATTERN_IRIS+") from CodeIris");
        }
        return code;
    }

    public boolean isPseudoIrisCode() {
       return code().endsWith(PSEUDO_IRIS_SUFFIX);
    }

    public String codeCommune() {
        return code().substring(0, 5);
    }
}
