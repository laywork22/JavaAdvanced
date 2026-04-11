package main;

import acquisti.Cliente;
import acquisti.Ordine;
import acquisti.Prodotto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTwo {
    public static void main(String[] args){
        Prodotto p1 = new Prodotto(0, "Penna", "Cartoleria", 0.50);
        Prodotto p2 = new Prodotto(1, "Cuffie auricolari", "Musica", 20.99);
        Prodotto p3 = new Prodotto(4, "Delitto e Castigo", "Libri", 13.0);
        Prodotto p4 = new Prodotto(234, "The Book", "Libri", 110.0);
        Prodotto p5 = new Prodotto(345, "Kung Fu Panda", "Film", 10.0);

        Cliente c = new Cliente(21345, "Luigi Rossi", 2);
        Cliente c2 = new Cliente(76423, "Marco Ferraioli", 3);

        Ordine o = new Ordine(12456, LocalDate.now(), c, "In elaborazione", LocalDate.now().plusDays(10));
        o.aggiungi(p1);
        o.aggiungi(p2);
        o.aggiungi(p3);
        o.aggiungi(p4);

        Ordine o2 = new Ordine(432523, LocalDate.now().minusDays(3), c2,"In Consegna", LocalDate.now().plusDays(1));
        o2.aggiungi(p3);
        o2.aggiungi(p5);

        System.out.println(o2);

        List<Ordine> l = new ArrayList<>();
        l.add(o);
        l.add(o2);

        Set<Ordine> so = l.stream().filter(o1 -> o1.stream().
                        anyMatch(p -> p.getCategoria().equals("Libri"))).
                collect(Collectors.toSet());

        System.out.println(l);
    }

}
