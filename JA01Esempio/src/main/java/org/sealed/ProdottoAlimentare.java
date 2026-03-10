package org.sealed;

/*
    final -> non ammetto più sottoclassi, blocco la gerarchia
    sealed -> continuo la gerarchia di classi sigillate
    non-sealed -> rimuovo il sigillo
* */
public non-sealed class ProdottoAlimentare extends Prodotto{
    public boolean isAlimentare() {
        return true;
    }
}
