package main;

import acquisti.Cliente;
import acquisti.Ordine;
import acquisti.Prodotto;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class MainThree {
    public static void main(String[] args) {
        Prodotto p1 = new Prodotto(0, "Penna", "Cartoleria", 0.50);
        Prodotto p2 = new Prodotto(1, "Cuffie auricolari", "Musica", 20.99);
        Prodotto p3 = new Prodotto(4, "Delitto e Castigo", "Libri", 13.0);
        Prodotto p4 = new Prodotto(234, "Focus", "Riviste", 13.0);
        Prodotto p5 = new Prodotto(345, "Kung Fu Panda", "Film", 10.0);
        Prodotto p6 = new Prodotto(878, "Vevo", "Riviste", 6.0);

        Cliente c = new Cliente(21345, "Luigi Rossi", 2);
        Cliente c2 = new Cliente(76423, "Marco Ferraioli", 3);

        Ordine o = new Ordine(12456, LocalDate.now(), c, "In elaborazione", LocalDate.now().plusDays(10));
        o.aggiungi(p1);
        o.aggiungi(p2);
        o.aggiungi(p3);
        o.aggiungi(p4);
        o.aggiungi(p5);
        o.aggiungi(p6);

        o.stream().
                filter(p -> p.getCategoria().equals("Riviste")).
                forEach(p -> p.setPrezzo(p.getPrezzo() - p.getPrezzo()/10));

        Set<Prodotto> rivisteScontate = o.stream().
                filter(p -> p.getCategoria().equals("Riviste")).
                collect(Collectors.toSet());

        System.out.println(rivisteScontate);
    }
}
