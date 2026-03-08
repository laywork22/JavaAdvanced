package org.record;

/**
 * Un Record rappresenta un oggetto immutabile in Java.
 * Serve per istanziare una classe in una maniera minimale.
 * <p>
 * Attributi privati e final.
 * I getter hanno una notazione particolare: solo il nome dell'attributo es. nome()
 * Non si possono aggiungere altri attributi, equals e hashcode calcolati sugli attributi.
 * Anche la toString() è già definita.
 * <p>
 * Il record permette di ridefinire il costruttore. Ve ne sono di tre tipi. (1)
 */
public record Studente(String nome, String cognome, String matricola) {
    private static int count = 0;

    public static int getCount() {
        return count;
    }

    /**
     * Costruttore canonico (1.1)

    public Studente(String nome, String cognome, String matricola) {
        if (!matricola.matches("[0-9]{10}")) {
            throw new IllegalArgumentException();
        }

        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;

        count++;
    }*/

    /**
     * Costruttore canonico compatto (1.2)
     */
    public Studente {
        if (!matricola.matches("[0-9]{10}")) throw new IllegalArgumentException();
    }

    /**
     * Costruttore non canonico (1.3)
     */
    public Studente(String nome, String cognome) {
        this(nome, cognome, "06127" + String.format("%05d", ++count ));

    }
}
