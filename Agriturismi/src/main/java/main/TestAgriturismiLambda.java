package main;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TestAgriturismiLambda {
    public static void main(String[] args) {
        ElencoAgriturismi ea = ElencoAgriturismi.carica("src/main/Agriturismi-Napoli.csv");

        // Aggiornare il valore dell'attributo pernottamento
        // (inizializzato a FALSE durante la lettura) sulla base
        // della disponibilità di posti letto
        Objects.requireNonNull(ea).aggiorna((a) -> {
            if (a.getPostiLetto() > 0) {
                a.setPernottamento(true);
            }
        });

        //- aggiornare il valore dell'attributo camping
        // (inizializzato a FALSE durante la lettura) sulla
        // base dei posti tenda/roulotte
        ea.aggiorna((a) -> {
            if (a.getPostiRoulotte() > 0 || a.getPostiTenda() > 0) {
                a.setCamping(true);
            }
        });

        //esportare l'elenco dei comuni che ospitano Agriturismi
        Set<String> comuni = ea.esporta(a -> a.getComune().trim());
        System.out.println("==== ELENCO COMUNI CON AGRITURISMI ====");
        System.out.println(comuni);

        ea.ordina((a1, a2) -> a1.getDenominazione().compareToIgnoreCase(a2.getDenominazione()));

        ElencoAgriturismi gragnano = ea.filtra(a -> a.getComune().trim().equalsIgnoreCase("Gragnano"));
        int totaleLettiGragnano = gragnano.somma(a -> a.getPostiLetto());

        System.out.println("--- STATISTICHE GRAGNANO ---");
        System.out.println("Numero di posti letto complessivi negli agriturismi di Gragnano: " + totaleLettiGragnano);
    }
}
