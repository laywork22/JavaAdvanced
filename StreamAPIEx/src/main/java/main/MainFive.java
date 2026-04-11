package main;

import acquisti.Cliente;
import acquisti.Ordine;
import acquisti.Prodotto;

import java.time.LocalDate;
import java.util.Optional;

public class MainFive {
    public static void main(String[] args) {
        Prodotto p1 = new Prodotto(0, "Penna", "Cartoleria", 0.50);
        Prodotto p2 = new Prodotto(1, "Cuffie auricolari", "Musica", 20.99);
        Prodotto p3 = new Prodotto(4, "Delitto e Castigo", "Libri", 13.0);
        Prodotto p4 = new Prodotto(234, "Focus", "Riviste", 13.0);
        Prodotto p5 = new Prodotto(345, "Kung Fu Panda", "Film", 10.0);
        Prodotto p6 = new Prodotto(878, "Vevo", "Riviste", 6.0);
        Prodotto p7 = new Prodotto(321, "Le Sette Morti di Eveline Hardcastle", "Libri", 16.99);

        Cliente c = new Cliente(21345, "Luigi Rossi", 2);

        Ordine o = new Ordine(12456, LocalDate.now(), c, "In elaborazione", LocalDate.now().plusDays(10));
        o.aggiungi(p1);
        o.aggiungi(p2);
        o.aggiungi(p3);
        o.aggiungi(p4);
        o.aggiungi(p5);
        o.aggiungi(p6);
        o.aggiungi(p7);

        Optional<Prodotto> p = o.stream().
                sorted((pp1, pp2) -> Double.compare(pp1.getPrezzo(), pp2.getPrezzo())).
                findFirst();

        // Optional<Prodotto> p = o.stream().min((pp1, pp2) -> Double.compare(pp1.getPrezzo(), pp2.getPrezzo())):

        System.out.println(p.get());


    }
}
