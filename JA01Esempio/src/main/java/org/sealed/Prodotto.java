package org.sealed;

/*
Una classe sigillata può evitare di dichiarare le classi permesse se queste ultime sono dichiarate nello stesso file!
Se ne ho almeno una al di fuori del file, allora devo specificare il permits!

public sealed class Prodotto {...}
final class ProdottoNonAlimentare {...}
final class ProdottoALimentare {...}

!Nello stesso file della classe sigillata!

Tutte le sottoclassi di una classe sigillata devono stare nello stesso package o al più nello stesso modulo
 */
public sealed class Prodotto permits ProdottoAlimentare, ProdottoNonAlimentare {
    private static int count = 0;
    private final int id;

    {
        id = ++count;
    }


    public int getID() {
        return id;
    }

    public boolean isAlimentare() {return true;}
}

/*
Posso dichiarare una classe nello stesso file di una classe sigillata ma deve essere una
specializzazione di quest'ultima.

Può essere final, sealed o non-sealed e solo pubblica
 */
non-sealed class ProdottoNonAlimentare extends Prodotto {
    public boolean isNonAlimentare() {return true;}
}