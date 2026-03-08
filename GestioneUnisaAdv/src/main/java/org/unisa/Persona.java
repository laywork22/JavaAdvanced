package org.unisa;

public class Persona {
    private final String nome;
    private final String cognome;
    private final String codiceFiscale;

    public Persona(String nome, String cognome, String codiceFiscale) {
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !this.getClass().equals(o.getClass())) return false;

        if (this == o) return true;

        Persona p = (Persona) o;

        return this.getCodiceFiscale().equals(p.getCodiceFiscale());
    }

    @Override
    public String toString() {
        return "Codice Fiscale: " + codiceFiscale + ", Nome: " + nome + ", Cognome: " + cognome;
    }
}
