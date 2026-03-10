package gestionescrivania;

public class TestScrivania {
    public static void main(String[] args) {
        Appoggiabile a1 = new Computer("Macbook Pro");
        Appoggiabile a2 = new Lampada("blu", 100);
        Appoggiabile a3 = new Libro("Basi di dati", "Atzeni", "998325312");
        PortaPenne pp = new PortaPenne();
        pp.add(new Penna("giallo"));

        Scrivania s = new Scrivania();

        s.add(a1);
        s.add(a2);
        s.add(a3);
        s.add(pp);


        System.out.println("=== TEST SCRIVANIA ===");

        Studente st = new Studente();

        st.usa(s);
    }
}
