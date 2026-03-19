package it.unisa.csvframework;

public class Studente extends Persona{
    @CSV
    private String matricola;

    public Studente(String nome, String codiceFiscale, String cognome, String matricola) {
        super(nome, codiceFiscale, cognome);
        this.matricola = matricola;
    }

    public Studente() {}

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    @Override
    public String toString() {
        return "Studente{" +
                "matricola='" + matricola + '\'' +
                "} " + super.toString();
    }
}
