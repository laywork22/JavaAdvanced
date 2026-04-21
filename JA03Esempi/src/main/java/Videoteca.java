import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

public class Videoteca {
    private final List<Film> videoteca;

    public Videoteca() {
        videoteca = new ArrayList<>();
    }

    public void aggiungi(Film f) {
        videoteca.add(f);
    }

    public void aggiorna(AggiornaFilm af) {
        for (Film f : videoteca) {
            af.aggiornaFilm(f);
        }
    }

    public void aggiornaC(Consumer<Film> cf) {
        for (Film f: videoteca) {
            cf.accept(f);
        }
    }

    public Videoteca filtra(FiltroFilm ff) {
        Videoteca v = new Videoteca();

        for (Film f: videoteca) {
            if (ff.verifica(f)) {
                v.aggiungi(f);
            }
        }

        return v;
    }

    public Videoteca filtraP(Predicate<Film> p) {
        Videoteca v = new Videoteca();

        for (Film f : videoteca) {
            if (p.test(f)) {
                v.aggiungi(f);
            }
        }

        return v;
    }

    public <T> Collection<T> estrai(EstraiCampo<T> et) {
        List<T> campi = new ArrayList<>();

        for(Film f: videoteca) {
            campi.add(et.estraiCampo(f));
        }

        return campi;
    }

    //Vorrei generalizzare l'istanza della collezioe
    public <T> Collection<T> estraiF(Function<Film, T> f) {
        List<T> campi = new ArrayList<>();

        for (Film fc: videoteca) {
            campi.add(f.apply(fc));
        }

        return campi;
    }

    public <T> Collection<T> collezione(Supplier<Collection<T>> s, Function<Film, T> f ) {
        Collection<T> c = s.get();

        for (Film fc: videoteca) {
            c.add(f.apply((fc)));
        }

        return c;
    }

    public <T> Collection<T> collezionaAdv(Supplier<Collection<T>> s, Function<Film, T> f, BiConsumer<Collection<T>, T> cf) {
        Collection<T> c = s.get();

        for (Film fc: videoteca) {
            cf.accept(c, f.apply(fc));
        }

        return c;
    }

    public int sommaValutazione() {
        int sum = 0;

        for (Film f: videoteca) {
            sum += f.getValutazione();
        }

        return sum;
    }

    public int somma(Function<Film, Integer> ff) {
        int sum = 0;

        for (Film f: videoteca) {
            sum += ff.apply(f);
        }

        return sum;
    }

    /*
     * Problema 1: non riusciamo a instanziare l'accumulatore -> passiamo un elemento neutro
     * Problema 2: l'operazione -> usiamo un BinaryOperator
     */
    public <T> T sommaAdv(T neutro, BinaryOperator<T> b,Function<Film, T> ff) {
        T accumulatore = neutro;

        for (Film f: videoteca) {
            accumulatore = b.apply(accumulatore, ff.apply(f));
        }

        return neutro;
    }

    public Stream<Film> stream() {
        return videoteca.stream();
    }

    @Override
    public String toString() {
        return "Videoteca{" +
                "videoteca=" + videoteca +
                '}';
    }
}
