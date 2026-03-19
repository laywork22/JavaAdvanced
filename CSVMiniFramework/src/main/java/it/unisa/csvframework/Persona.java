package it.unisa.csvframework;

public class Persona {
    @CSV
    private String nome;
    @CSV
    private String cognome;
    @CSV
    private String codiceFiscale;

    public Persona(String nome, String codiceFiscale, String cognome) {
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
        this.cognome = cognome;
    }

    public Persona() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                '}';
    }
}
