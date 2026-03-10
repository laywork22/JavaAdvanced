package gestionescrivania;

public class Studente {
    public void usa(Scrivania s) {
        for (Appoggiabile a : s.getAppoggiabili()) {
            switch(a) {
                case Computer c -> System.out.println(c);
                case Lampada l -> System.out.println(l);
                case Penna p -> System.out.println(p);
                case PortaPenne pp -> System.out.println(pp);
                case Libro lb -> System.out.println(lb);
            }
        }
    }
}
