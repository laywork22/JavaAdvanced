package org.sealed;

import org.inter.Descrivibile;

/**
 * Non può implementare direttamente Espressione perchè la classe non è nella lista di quelle ammesse
 */
public class EspressioneSomma implements Descrivibile  {
    private final int[] vet;

    /*
    Argomenti variabili --> inserisco un numero variabile di
    argomenti dello stesso tempo.
     */
    public EspressioneSomma(int...x) {
        vet = new int[x.length];

        for (int i = 0; i < x.length; i++) {
            vet[i] = x[i];
        }
    }

    @Override
    public void descrivi() {

    }

    @Override
    public int valuta() {

        int somma = 0;

        for (int i = 0; i < vet.length; i++) {
            somma += vet[i];
        }

        return somma;
    }
}
