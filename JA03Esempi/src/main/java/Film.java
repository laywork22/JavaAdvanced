import java.util.Objects;

public class Film {
    private String titolo;
    private int valutazione;
    private Genere genere;

    public Film(String titolo, int valutazione, Genere genere) {
        this.titolo = titolo;
        this.valutazione = valutazione;
        this.genere = genere;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;
        return getValutazione() == film.getValutazione() && Objects.equals(getTitolo(), film.getTitolo()) && getGenere() == film.getGenere();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getTitolo());
        result = 31 * result + getValutazione();
        result = 31 * result + Objects.hashCode(getGenere());
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "titolo='" + titolo + '\'' +
                ", valutazione=" + valutazione +
                ", genere=" + genere +
                '}';
    }
}
