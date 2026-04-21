//Restituisce un tipo di quell'oggetto, sostituto del costruttore
public interface FilmFactory {
    Film getFilm(String titolo, int valutazione, Genere genere);
}
