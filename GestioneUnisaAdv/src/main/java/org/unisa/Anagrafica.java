package org.unisa;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Anagrafica {
    private final Map<String, PersonaUnisa> mappaMembri;

    public Anagrafica() {
        this.mappaMembri = new HashMap<>();
    }

    public void add(PersonaUnisa pu) {
        if (mappaMembri.containsKey(pu.getMatricola())) {
            System.out.println("Matricola già presente!");
        }
        else {
            mappaMembri.put(pu.getMatricola(), pu);
        }
    }

    public PersonaUnisa remove(String matricola) {
        return mappaMembri.remove(matricola);
    }

    public void writeCsv(String nomeFile) {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)))) {
            for (PersonaUnisa pu : mappaMembri.values()) {
                pw.append(pu.getCodiceFiscale()).append(";").append(pu.getNome()).append(";").
                append(pu.getCognome()).append(";").append(pu.getMatricola()).append(";");

                String ruolo = pu.getClass().getSimpleName().toLowerCase();
                pw.append(ruolo).append(";");

                //jdk 17 non supporta a pieno gli switch statement, devo ricorrere agli if-else
                if (pu instanceof Studente s) {
                    pw.print(s.getVotoMedio());
                    pw.append(";");
                    pw.println(s.getCorsoDiStudi());
                }
                else if (pu instanceof Docente d) {
                    pw.println(d.getInsegnamento());
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file");
        }
    }

    public static Anagrafica readCSV(String nomeFile) {
        Anagrafica a = new Anagrafica();

        try(BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] campi = line.split(";");


                String tipo = campi[4];

                PersonaUnisa pu = switch (tipo) {
                    case "studente" ->new Studente(campi[1], campi[2], campi[0],campi[3],
                            Float.parseFloat(campi[5]), CorsoDiStudi.valueOf(campi[6]));
                    case "docente" -> new Docente(campi[1], campi[2], campi[0], campi[3], campi[5]);
                    default -> throw new IllegalArgumentException("Tipo ignoto" + tipo);
                };

                a.add(pu);
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file");
        }

        return a;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, PersonaUnisa> entry : mappaMembri.entrySet()) {
            sb.append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
