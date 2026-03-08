package org.enumswitch;


public class Semaforo {
    private Colore c;

    public Semaforo(Colore c) {
        this.c = c;
    }

    public void indicazioni() {
        switch(c) {
            case ROSSO:
                System.out.println("Non attraversare l'incrocio");
                break;
            case GIALLO:
                System.out.println("Liberare rapidamente l'incrocio");
                break;
            case VERDE:
                System.out.println("È possibile attraversare l'incrocio");
                break;
            case NERO:
                System.out.println("Semaforo spento");
                break;
        }
    }

    public void indicazioniNS() {
        /**
         * Si possono accorpare case in questo modo:
         * case ROSSO,VERDE -> ...
         */
        switch(c) {
            case ROSSO -> System.out.println("Non attraversare l'incrocio");
            case GIALLO -> System.out.println("Liberare rapidamente l'incrocio");
            case VERDE -> System.out.println("È possibile attraversare l'incrocio");
            case NERO -> System.out.println("Semaforo spento");
        }
    }

    public void testSwitchExp() {

        /**
         * var rileva automaticamente il tipo da restituire della switch expression.
         * Non si può utilizzare come parametri dei metodi o su un attributo di una classe
         * <p></p>
         * Inferisce il tipo dell'oggetto a tempo di compilazione
         * Il compilatore si mette nel caso minimo supertipo comune se uno switch è usato
         * -> nel caso peggiore sceglie Object
         */
        var msg = switch (c) {
            case ROSSO -> new Semaforo(Colore.GIALLO);
            case GIALLO -> "Liberare rapidamente l'incrocio";
            case VERDE -> "È possibile attraversare l'incrocio";
            case NERO -> {
                int a,b;

                a = 2;
                b = 3;

                yield a+b;
            }
        };


    }

    @Override
    public String toString() {

        var msg1 = switch(c) {
            case ROSSO -> "Non attraversare l'incrocio";
            case GIALLO -> "Liberare rapidamente l'incrocio";
            case VERDE -> "È possibile attraversare l'incrocio";
            case NERO -> "Semaforo spento";
        };


        /**
         * Espressione switch --> lo switch nuovo può restituire un valore a seconda del caso
         * Bisogna garantire l'esaustività -> deve essere in grado di restituire qualcosa in ogni caso,
         * es. Per gli enum tutti i casi, per altre classi si usa il default.
         * <p>
         * Se togliamo un caso, il compilatore da errore!
         */
        return switch(c) {
            case ROSSO -> "Non attraversare l'incrocio";
            case GIALLO -> "Liberare rapidamente l'incrocio";
            case VERDE -> "È possibile attraversare l'incrocio";
            case NERO -> "Semaforo spento";
        };
    }

    public Colore getC() {
        return c;
    }

    public void setC(Colore c) {
        this.c = c;
    }
}
