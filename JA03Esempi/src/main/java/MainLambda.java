import java.util.*;

public class MainLambda {
    public static void main(String[] args) {
        Videoteca v = new Videoteca();
        
        v.aggiungi(new Film("Benvenuti al Sud", 8, Genere.COMMEDIA));
        v.aggiungi(new Film("Inception", 9, Genere.THRILLER));
        v.aggiungi(new Film("Matrix", 9, Genere.AZIONE));
        v.aggiungi(new Film("Benvenuti al Nord", 6, Genere.COMMEDIA));
        v.aggiungi(new Film("Titanic", 7, Genere.DRAMMATICO));
        v.aggiungi(new Film("Fast And Furious", 10, Genere.AZIONE));

        System.out.println(v);

        //fluent style
        Videoteca v1 = v.filtra((f) -> f.getValutazione() > 7).filtra(f -> f.getGenere() == Genere.THRILLER);
        System.out.println(v1);

        Videoteca v2 = v.filtraP(f -> f.getGenere() == Genere.COMMEDIA);
        //v2.aggiorna(f -> f.setValutazione(9));
        v2.aggiornaC(FiltriEAggiornamenti::boostValutazioni);
        System.out.println(v2);

        Videoteca v3 = v.filtra(FiltriEAggiornamenti::isBelFilm);
        System.out.println(v3);

        FiltriEAggiornamenti fea = new FiltriEAggiornamenti();
        Videoteca v4 = v.filtra(fea::isCommedia);
        System.out.println(v4);

        Collection<?> c = v.estrai(Film::getTitolo);
        Collection<?> c1 = v.estrai(Film::getValutazione);
        System.out.println(c);
        System.out.println(c1);

        FilmFactory ff = ( titolo, valutazione,genere) -> new Film(titolo, valutazione, genere);
        Film film = ff.getFilm("Harry Potter", 4, Genere.AZIONE);
        System.out.println(film);


        FilmFactory ff2 = Film::new;

        Collection<?> c2 = v.collezione(ArrayList::new, Film::getValutazione);
        System.out.println("collezione titoli: " + c2);

        Collection<?> c3 = v.collezionaAdv(HashSet::new, Film::getValutazione, Collection::add);
        System.out.println("collezione adv: " + c3);

        int somma = v.sommaValutazione();
        System.out.println("somma valutazioni: " + somma);

        int somma2 = v.somma(Film::getValutazione);
        System.out.println("somma valutazioni pt2: " + somma2);

        int ris = v.sommaAdv(0, Integer::sum, Film::getValutazione );
        System.out.println("somma valutazionie advanced: " + ris);

        String titoli = v.sommaAdv("", (a,b) -> a + " " + b, Film::getTitolo);
        System.out.println("titoli: " + titoli);

        int ris2 = v.sommaAdv(0, (a,b) -> a>b ? a : b, Film::getValutazione);
        System.out.println("somma valutazioni con condizione: " + ris2);

    }
}
