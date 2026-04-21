package it.unisa;

import java.util.Objects;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main() {
        /*
         * caricare un elenco agriturismi
         * da file tramite stream API mediante
         * uso della classe Files
         */
        ElencoAgriturismi ea = ElencoAgriturismi.importaAgriturismi("Agriturismi-Benevento.csv");

        /*
         * aggiornare il valore dell'attributo pernottamento
         * (inizializzato a FALSE durante la lettura)
         * sulla base della disponibilità di posti letto
         *
         * - aggiornare il valore dell'attributo camping
         * (inizializzato a FALSE durante la lettura)
         * sulla base dei posti tenda/roulotte
         */
        ea.aggiornaCamping();
        ea.aggiornaPernottamento();

        System.out.println("===== AGRITURISMI IMPORTATI =====\n" + ea);

        //esportare l'elenco dei comuni che ospitano Agriturismi
        Set<String> comuni = ea.importaComuniOspitanti("Agriturismi-Benevento.csv");
        System.out.println("==== ELENCO COMUNI CON AGRITURISMI ====");
        System.out.println(comuni);

        //ordinare l'intero elenco alfabeticamente per denominazione azienda
        System.out.println("===== ELENCO AGRITURISMI ORDINATI PER DENOMINAZIONE =====");
        System.out.println(ea.ordinaPerDenominazione() + "\n");

        //indicare il comune con il maggior numero di posti campeggio
        System.out.println("===== COMUNE CON IL MAGGIOR NUMERO DI POSTI CAMPEGGIO =====\n" + ea.getComuneMaxPostiCampeggio());

        //ottenere una mappa (Map<K,V>) con il numero di posti letto
        // complessivi disponibili per ogni comune
        System.out.println("===== POSTI LETTO DISPONIBILI PER CIASCUN COMUNE =====\n" + ea.getPostiLettoTotaliPerComune());

        //ottenere una mappa con il numero medio di posti camping degli agriturismi di ogni comune
        System.out.println("===== NUMERO MEDIO POSTI CAMPING DEGLI AGRITURISMI DI OGNI COMUNE =====\n" + ea.getNumeroMedioPostiCampingPerComune());

        /*
         * Definito un record Titolare con attributi (nome, cognome, mail)
         * ottenere un elenco (lista) di tutti titolari.
         * Laddove la mail non fosse definita, impostare una mail di default ( "info@agriturismibenevento.it")
         */
        System.out.println("===== LISTA DI TITOLARI =====\n" + ea.getTitolari());

    }
}


