package gestionescrivania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scrivania {
    private final List<Appoggiabile> appoggiabili;

    public Scrivania() {
        appoggiabili = new ArrayList<>();
    }


    public List<Appoggiabile> getAppoggiabili() {
        return Collections.unmodifiableList(appoggiabili);
    }

    public void add(Appoggiabile a) {
        appoggiabili.add(a);
    }

    public Appoggiabile remove(Appoggiabile a) {
        int i = appoggiabili.indexOf(a);

        if (i >= 0) {
            return appoggiabili.remove(i);
        } else return null;
    }
}
