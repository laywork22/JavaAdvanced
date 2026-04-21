package main;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

public class TestAgriturismiReference {
    public static void main(String[] args) {
        ElencoAgriturismi ea = ElencoAgriturismi.carica("src/main/Agriturismi-Napoli.csv");

        Objects.requireNonNull(ea).aggiorna(ea::aggiornaPernottamento);

        ea.aggiorna(ea::aggiornaCamping);

        Set<String> comuni = ea.esporta(ElencoAgriturismi::trovaComuni);
        System.out.println(comuni);

        ea.ordina(Comparator.comparing(Agriturismo::getDenominazione));
        System.out.println(ea);


        ElencoAgriturismi gragnano = ea.filtra(ea::getPostiLettoGragnano);
        System.out.println("\n--- ELENCO AGRITURISMI GRAGNANO ---\n");
        System.out.println(gragnano);

        int totaleLettiGragnano = gragnano.somma(Agriturismo::getPostiLetto);
        System.out.println("--- STATISTICHE GRAGNANO ---");
        System.out.println("Numero di posti letto complessivi negli agriturismi di Gragnano: " + totaleLettiGragnano);
    }
}
