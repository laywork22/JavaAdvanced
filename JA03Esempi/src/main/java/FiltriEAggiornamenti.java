public class FiltriEAggiornamenti {
    public static boolean isBelFilm(Film f) {
        return f.getValutazione() > 7;
    }

    public static void boostValutazioni(Film f) {
        f.setValutazione(10);
    }

    public boolean isCommedia(Film f) {
        return f.getGenere() == Genere.COMMEDIA;
    }


}
