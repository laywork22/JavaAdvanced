import java.util.List;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) {
        Videoteca v = new Videoteca();

        v.aggiungi(new Film("Benvenuti al Sud", 8, Genere.COMMEDIA));
        v.aggiungi(new Film("Inception", 9, Genere.THRILLER));
        v.aggiungi(new Film("Matrix", 9, Genere.AZIONE));
        v.aggiungi(new Film("Benvenuti al Nord", 6, Genere.COMMEDIA));
        v.aggiungi(new Film("Titanic", 7, Genere.DRAMMATICO));
        v.aggiungi(new Film("Fast And Furious", 10, Genere.AZIONE));

        //Operazione lazy: fino a quando non è eseguita un'operazione terminale, quelle intermedie non saranno eseguite
        Stream<Film> s1 = v.stream().filter(f -> f.getValutazione() > 7);
        s1.forEach(System.out::println);
        /*quando definiamo e otteniamo lo stream può essere consumato una sola volta
          si basa sul concetto di iteratore -> split iterator per parallelizzazione
          La toString non è definita
          la catena si esaurisce alla prima invocazione terminale
        */

        //s1.forEach(System.out::println);


        v.stream().forEach(f -> f.setValutazione(10)); //pratica di progettazione SBAGLIATA!
                                                            // Uno stream deve solo consumare la
                                                            //sorgente e non alterarla!!!
        Stream<String> s2 = v.stream().filter(f -> f.getValutazione() > 6).map(Film::getTitolo);
        //s2.forEach(System.out::println);

        List<?> titoli = s2.toList();

        System.out.println(titoli);

        //int somma2 = v.stream().map(f -> f.getValutazione()).reduce(0, (a,b) -> a+b);

        int somma = v.stream().mapToInt(Film::getValutazione).sum();
        System.out.println(somma);

        //se volessi avere corrispondenze 1:N in uno stream, esempio tutte le parole di un titolo si usa una flatMap
        //FlatMap fa sì che la cardinalità dello stream di input è diversa daquella dello stream di output, solitamente è maggiore
        //Si fondono vari stream in un unico stream
        v.stream().map(Film::getTitolo).flatMap(t -> Stream.of(t.split("\\s"))).forEach(System.out::println);

        //Average prevede la divisione per il numero di elementi
        //OptionalDouble è un wrapper
        double average = v.stream().mapToInt(Film::getValutazione).filter(vt -> vt > 10).average().orElse(0.0);

        //NB. Ogni elemento della visita subisce tutte le trasformazioni una volta nella catena e poi alla fine viene valutato il
        //metodo terminale che raccoglie tutto e opera.

        System.out.println("Media dei voti: " + average);
    }
}
