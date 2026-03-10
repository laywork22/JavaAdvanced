package gestionescrivania;

import java.util.ArrayList;
import java.util.List;

public final class PortaPenne implements Appoggiabile {
    private final List<Penna> penne;

    public PortaPenne() {
        penne = new ArrayList<>();
    }

    public void add(Penna p) {
        penne.add(p);
    }

    public Penna rimuovi(Penna p) {
        int i = penne.indexOf(p);

        if (i >= 0) {
            return penne.remove(i);
        } else return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Penna p : penne) {
            sb.append(p);
        }

        return sb.toString();
    }
}
