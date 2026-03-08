package org.enumswitch;

public class TestEnum {
    public static void main(String[] args) {
        /**
         * Non si può istanziare un enum con new
         */
        Colore c = Colore.NERO;

        System.out.println("name:" + c.name() + " ordinal: " + c.ordinal());
        System.out.println(c);

        var s = new Semaforo(c);
        s.indicazioni();
        s.indicazioniNS();

        System.out.println(s);

        /*
         * C'è un problema con gli array con var cioè
         * si può usare solo ponendo dopo var il nome di un normale oggetto (non array) e
         * poi tramite inferenza ottiene il tipo corretto.
         * SBAGLIATO: var[] vett = new int[3];
         * Il compilatore non capisce che oggetto è var.
         * si fa in questo caso
         */
        var vett = new int[3];

    }
}
