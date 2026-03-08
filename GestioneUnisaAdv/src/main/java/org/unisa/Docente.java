package org.unisa;

public final class Docente extends PersonaUnisa {
    private final String insegnamento;

    public Docente(String nome, String cognome, String codiceFiscale, String matricola, String insegnamento) {
        super(nome, cognome, codiceFiscale, matricola);
        this.insegnamento = insegnamento;
    }

    public String getInsegnamento() {
        return insegnamento;
    }

    @Override
    public String toString() {
        return super.toString() + ", Insegnamento: " + insegnamento;
    }
}
