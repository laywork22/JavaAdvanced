package org.sealed;

import java.util.ArrayList;
import java.util.List;

public class NewMain {
    public static void main(String[] args) {
        Prodotto p = new Prodotto();

        ProdottoAlimentare pa = new ProdottoAlimentare();
        ProdottoAlimentare pa2 = new ProdottoAlimentare();
        ProdottoAlimentare pa3 = new ProdottoAlimentare();
        ProdottoAlimentare pa4 = new ProdottoAlimentare();

        ProdottoNonAlimentare pna = new ProdottoNonAlimentare();

        List<Prodotto> l = new ArrayList<>();

        l.add(p);
        l.add(pa);
        l.add(pa2);
        l.add(pa3);
        l.add(pa4);
        l.add(pna);

        /**
         * Pattern matching: variabili locali dette scope di pattern che
         * sostituiscono il tedioso upcast.
         *
         * Se voglio verificare una non instanza di una classe, allora nello scope dell'if non sarà
         * utilizzabile ma nell'else si.
         */
        System.out.println("Pattern matching: ");
        for (Prodotto pc : l) {
            //Metodo classico
            if (pc instanceof ProdottoAlimentare) {
                ProdottoAlimentare pac = (ProdottoAlimentare) pc;

                System.out.println("ProdottoAlimentare? " + pac.isAlimentare());
            }

            if (!(pc instanceof ProdottoNonAlimentare pnac)) {
                System.out.println("Non è un prodotto alimentare");
            } else {
                System.out.println("ProdottoNonAlimentare? " + pnac.isNonAlimentare() + "\n");
            }
        }

        /**
         * Patter matching per switch!
         * Quando si implementa il pattern matching per switch deve
         * essere sempre verificata la proprietà di esaustività.
         * <p></p>
         * Non è necessario per gerarchie sigillate
         * ma se invece sono presente nodi non sigillati non coperti (ad
         * esempio non abbiamo messo la superclasse) e quindi è meglio mettere un default
         * perchè da errore in caso contrario.
         *
         * Guarded Pattern -> Si possono mettere più condizioni affinché quel
         * caso sia raggiungibile.
         */
        System.out.println("Pattern matching e guarded pattern per switch: ");
        for (Prodotto pc : l) {
            switch(pc) {
                case ProdottoAlimentare pac when pac.getID() > 2 -> {
                    System.out.println("ProdottoAlimentare? " + pac.isAlimentare());
                }
                case ProdottoNonAlimentare pnac -> {
                    System.out.println("ProdottoNonAlimentare? " + pnac.isNonAlimentare());
                }
                /*
                case Prodotto t -> {
                    System.out.println("Prodotto? ");
                }
                 */
                default -> {
                    System.out.println("Caso default");
                }
            }
        }
    }
}
