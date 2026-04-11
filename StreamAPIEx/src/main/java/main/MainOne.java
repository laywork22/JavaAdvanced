package main;

import acquisti.Cliente;
import acquisti.Ordine;
import acquisti.Prodotto;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOne {
    public static void main(String[] args){
        Prodotto p1 = new Prodotto(0, "Penna", "Cartoleria", 0.50);
        Prodotto p2 = new Prodotto(1, "Cuffie auricolari", "Musica", 20.99);
        Prodotto p3 = new Prodotto(4, "Delitto e Castigo", "Libri", 13.0);
        Prodotto p4 = new Prodotto(234, "The Book", "Libri", 110.0);

        Cliente c = new Cliente(21345, "Luigi Rossi", 2);

        Ordine o = new Ordine(12456, LocalDate.now(), c, "In elaborazione", LocalDate.now().plusDays(10));
        o.aggiungi(p1);
        o.aggiungi(p2);
        o.aggiungi(p3);
        o.aggiungi(p4);

        Set<Prodotto> s1 = o.stream().
                filter(p -> p.getCategoria().equals("Libri") && p.getPrezzo() > 100.0).
                collect(Collectors.toSet());

        System.out.println("Ordine: \n" + s1);
    }

}
