package org.enumswitch;

/**
 * I tipi enum sono classi e possono avere tutte le caratteristiche
 * delle classi normali
 * Ogni elemento è una possibile istanza della classe.
 * <p></p>
 * Non è un tipo intrinsecamente immutabile.
 * Posso usare i set per modificare uno degli attributi.
 */
public enum Colore {
    ROSSO(255,0,0),
    VERDE(0,255,0),
    GIALLO(0,255,255),
    NERO(0,0,0);

    //Canali
    private final int R;
    private final int G;
    private final int B;

    /**
     * Se non specificato è private il costruttore
     * @param R
     * @param G
     * @param B
     */
    Colore(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    /**
     * Si possono introdurre vari metodi ma questi non possono modificare i campi dell'enumerazione.
     *
     * Metodi utili:
     * name() -> istanza
     * ordinal() -> numero progressivo associato a ciascuna istanza
     * values() -> iterazione sulle possibili istanze
     */

    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }

    @Override
    public String toString() {
        return this.name() + " -> R:" + this.R + " G:"+ this.G + " B:" + this.B;
    }


}
