package it.unisa;

public record Titolare(String nome, String cognome, String mail) {
    public Titolare(String nome, String cognome, String mail) {
        this.nome = nome;
        this.cognome = cognome;

        if (mail == null || mail.matches("^nd$")) this.mail = "info@agriturismibenevento.it";
        else this.mail = mail;
    }
}
