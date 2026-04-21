package it.unisa;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

public class ElencoAgriturismi {
    private List<Agriturismo> agriturismi;

    public ElencoAgriturismi() {
        agriturismi = new ArrayList<>();
    }

    public ElencoAgriturismi(List<Agriturismo> at) {
        this.agriturismi = new ArrayList<>(at);
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

    public Stream<Agriturismo> stream() {
        return agriturismi.stream();
    }

    public void aggiorna(Consumer<Agriturismo> ca) {
        for(Agriturismo a : agriturismi) {
            ca.accept(a);
        }
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


    //STREAM SECTION
    public void aggiornaPernottamento() {
        agriturismi.stream().
                filter(a -> a.getPostiLetto() > 0).
                forEach(a -> a.setPernottamento(true));
    }

    public void aggiornaCamping() {
        agriturismi.stream().
                filter(a -> a.getPostiRoulotte() > 0 || a.getPostiTenda() > 0).
                forEach(a -> a.setCamping(true));
    }

    public ElencoAgriturismi ordinaPerDenominazione() {
        return new ElencoAgriturismi(stream().
                sorted((a, b) -> a.getDenominazione().compareToIgnoreCase(b.getDenominazione())).
                toList());
    }

    public String getComuneMaxPostiCampeggio() {
        Optional<Agriturismo> comune =  stream().max(Comparator.comparingInt(a -> a.getPostiTenda() + a.getPostiRoulotte()));

        return comune.orElseThrow(() -> new NoSuchElementException("Comune non trovato")).getComune();
    }

    public Set<String> importaComuniOspitanti(String nomeFile) {

        try(Stream<String> agriturismi = Files.
                lines(Path.of("src","main", "resources", "data",nomeFile)).
                skip(1)) {

            return agriturismi.
                    map(line -> line.split(";")[0]).
                    collect(Collectors.toSet());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return new HashSet<>();
    }

    public Map<String, Integer> getPostiLettoTotaliPerComune() {
        return agriturismi.stream().
                collect(Collectors.groupingBy(Agriturismo::getComune,
                        Collectors.summingInt(Agriturismo::getPostiLetto)));
    }

    public Map<String, Double> getNumeroMedioPostiCampingPerComune() {
        return agriturismi.stream().
                collect(Collectors.groupingBy(Agriturismo::getComune,
                        Collectors.averagingDouble(a -> (double) (a.getPostiRoulotte() + a.getPostiTenda()))));
    }

    public List<Titolare> getTitolari() {
        return stream().distinct().map(a -> {
            String[] titolare = a.getTitolare().trim().split("\\s+");

            String cognome = titolare[0];
            String nome = titolare[1];

            return new Titolare(nome, cognome, a.getRecapiti());
        }).toList();
    }

    public static ElencoAgriturismi importaAgriturismi(String fileName) {

        try(Stream<String> agriturismoStream = Files.
                lines(Path.of("src","main", "resources", "data",fileName)).
                skip(1)) {

            return new ElencoAgriturismi(agriturismoStream.
                    map(line -> line.split(";")).
                    map(fields -> getAgriturismo(fields, fields[0])).
                    toList());

        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return new ElencoAgriturismi();
    }
}
