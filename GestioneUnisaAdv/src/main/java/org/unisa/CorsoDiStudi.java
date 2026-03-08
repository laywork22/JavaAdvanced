package org.unisa;

public enum CorsoDiStudi {
    LT("06127", "Laurea Triennale"),
    LM("06228", "Laurea Magistrale"),
    PhD("8805", "PhD");

    private final String prefisso;
    private final String nomeEsteso;


    CorsoDiStudi(String prefisso, String nomeEsteso) {
        this.prefisso = prefisso;
        this.nomeEsteso = nomeEsteso;
    }

    public String getPrefisso() {
        return prefisso;
    }

    public String getNomeEsteso() {
        return nomeEsteso;
    }
}
