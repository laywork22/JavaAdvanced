package main;

import java.io.*;
import java.util.*;
import java.util.function.*;

public class ElencoAgriturismi {
    private final List<Agriturismo> agriturismi;

    public ElencoAgriturismi() {
        agriturismi = new ArrayList<>();
    }

    public void aggiungi(Agriturismo a) {
        agriturismi.add(a);
    }

    public <T> Set<T> esporta(Function<Agriturismo, T> fa) {
        Set<T> list = new HashSet<>();

        for (Agriturismo a : agriturismi) {
            list.add(fa.apply(a));
        }

        return list;
    }

    public ElencoAgriturismi filtra(Predicate<Agriturismo> pa) {
        ElencoAgriturismi ea = new ElencoAgriturismi();

        for(Agriturismo a : agriturismi) {
            if (pa.test(a)) {
                ea.aggiungi(a);
            }
        }

        return ea;
    }

    public void aggiorna(Consumer<Agriturismo> ca) {
        for(Agriturismo a : agriturismi) {
            ca.accept(a);
        }
    }

    public static ElencoAgriturismi carica(String nomefile) {
        ElencoAgriturismi ea = new ElencoAgriturismi();

        try(BufferedReader br = new BufferedReader(new FileReader(nomefile))) {

            if (br.readLine() == null) return null;

            String line;

            while((line = br.readLine()) != null) {
                String[] campi = line.split(";", -1);

                String comune = campi[0];
                Agriturismo a = getAgriturismo(campi, comune);

                ea.aggiungi(a);

            }

        } catch (FileNotFoundException e) {
            System.out.println("File non trovato: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }

        return ea;
    }

    private static Agriturismo getAgriturismo(String[] campi, String comune) {
        String titolare = campi[1];
        String denominazione = campi[2];
        String indirizzo = campi[3];

        int postiLetto = 0;
        if (!campi[4].isEmpty()){
            postiLetto  = Integer.parseInt(campi[4]);
        }

        int postiMacchina = 0;
        if (!campi[5].isEmpty()) {
            postiMacchina = Integer.parseInt(campi[5]);
        }

        int postiRoulotte = 0;
        if (!campi[7].isEmpty()) {
            postiRoulotte = Integer.parseInt(campi[7]);
        }

        int postiTenda = 0;
        if (!campi[6].isEmpty()) {
            postiTenda = Integer.parseInt(campi[6]);
        }

        String recapiti = campi[8];

        return new Agriturismo(comune, titolare, denominazione,
                indirizzo, postiLetto, postiMacchina,
                postiRoulotte, recapiti, postiTenda);
    }

    public void ordina(Comparator<Agriturismo> ca) {
         agriturismi.sort(ca);
    }

    public int somma(ToIntFunction<Agriturismo> ta) {
        int somma  = 0;

        for (Agriturismo a : agriturismi) {
            somma += ta.applyAsInt(a);
        }

        return somma;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Agriturismo a : agriturismi) {
            sb.append(a.toString()).append("\n========\n");
        }

        return sb.toString();
    }

    //Metodi per il test con Method Reference.
    public void aggiornaPernottamento(Agriturismo a) {
        if (a.getPostiLetto() > 0) a.setPernottamento(true);
    }


    public void aggiornaCamping(Agriturismo a) {
        if (a.getPostiRoulotte() > 0 || a.getPostiTenda() > 0) {
            a.setCamping(true);
        }
    }

    //Metodo statico per Static Method Ref.
    public static String trovaComuni(Agriturismo a) {
        return a.getComune().trim();
    }

    public boolean getPostiLettoGragnano(Agriturismo a) {
        return "Gragnano".equalsIgnoreCase(a.getComune());
    }
}
