package org.unisa;

public sealed class PersonaUnisa extends Persona permits Docente, Studente {
    private final String matricola;

    public PersonaUnisa(String nome, String cognome, String codiceFiscale, String matricola) {
        super(nome, cognome, codiceFiscale);

        this.matricola = matricola;
    }

    public String getMatricola() {
        return matricola;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !this.getClass().equals(o.getClass())) return false;

        if (this == o) return true;

        PersonaUnisa pu = (PersonaUnisa) o;

        return this.matricola.equals(pu.getMatricola());
    }

    @Override
    public String toString() {
        return super.toString() + ", Matricola: " + matricola;
    }
}
