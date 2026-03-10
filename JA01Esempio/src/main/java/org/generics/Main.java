package org.generics;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Forma f = new Forma();

        Ellisse e = new Ellisse();

        Forma f1 = new Triangolo();

        Triangolo t = new Triangolo();

        List<Forma> l = new ArrayList<>();
        List<Triangolo> lf = new ArrayList<>();

        l.add(f);
        l.add(e);
        l.add(f1);

        /*
         * Una list di Forma non ha nessuna relaizone gerarchica con una lista
         * di Triangolo.
         * L'unica gerarchia in comune è Object
         *
         * <Vedere metodo visitaCollezione(...)
         */
        //List<Forma> l1 = new ArrayList<Triangolo>();

        visitaCollezione(lf);
        visitaCollezione(l);

        //visitaCollezione(new ArrayList<Integer>()); //Errore di compilazione
        aggiungiElemento(l, e);
        aggiungiElementoBis(l, f1);
        aggiungiElementoTris(lf, t);
    }

    /**
     * Wildcard "?"
     * Consente di accettare una lista di qualsiasi tipo.
     * <p>
     * Non posso assumere che sia una Forma, allora bisogna
     * usare Object.
     * <p>
     * Sulle wildcard è possibile vincolare la gerarchia come nei generics.
     */
    public static void visitaCollezione(List<? extends Forma> l) {
        for (Forma f : l) {
            System.out.println(f);
        }
    }

    /**
     * Potrei inserire una lista di triangoli e passare un'ellisse come forma il che
     * causa errori.
     * <p>
     * È possibile solo visitare la collezione ma non modificarla.
     */
    public static void aggiungiElemento(List<? extends Forma> l, Forma f) {
        for (Forma f1 : l) {
            System.out.println(f1);
        }

        //l.add(f)
    }

    // List<? super Forma> -> Gli elementi inseriti nella collezione sono al più di tipo Forma
    public static void aggiungiElementoBis(List<? super Forma> l, Forma f1) {
        l.add(f1);

        for (Object c : l) {
            System.out.println(c);
        }
    }

    //Si può risolvere con un generic
    public static <T extends Forma> void aggiungiElementoTris(List<T> l, T e) {
        l.add(e);
    }
}
