package org.unisa;

import java.util.HashMap;
import java.util.Map;

public non-sealed class Studente extends PersonaUnisa {
    private final float votoMedio;
    private final CorsoDiStudi corsoDiStudi;

    private final static class NumeroProgressivoMatricola {
        private final static Map<CorsoDiStudi, Integer> progressiveNumberMap;

        static {
            progressiveNumberMap = new HashMap<>();

            for (CorsoDiStudi cds : CorsoDiStudi.values()) {
                progressiveNumberMap.put(cds, 1);
            }
        }

        private static void aggiornaNumeroProgressivo(CorsoDiStudi corsoDiStudi) {
            int progressiveCounter = progressiveNumberMap.get(corsoDiStudi);

            progressiveNumberMap.replace(corsoDiStudi, progressiveCounter + 1);
        }

        private static Integer getNumeroProgressivo(CorsoDiStudi corsoDiStudi) {
            return progressiveNumberMap.get(corsoDiStudi);
        }

        private static void sincronizza(CorsoDiStudi cds, int numProg) {
            progressiveNumberMap.compute(cds, (k, v) -> {
                int attuale = (v == null) ? 1 : v;

                return Math.max(attuale, numProg + 1);
            });
        }
    }

    public Studente(String nome, String cognome, String codiceFiscale,
                    float votoMedio, CorsoDiStudi corsoDiStudi) {
        super(nome, cognome, codiceFiscale,
                String.format("%s" + "%05d", corsoDiStudi.getPrefisso(),
                        NumeroProgressivoMatricola.getNumeroProgressivo(corsoDiStudi)));

        NumeroProgressivoMatricola.aggiornaNumeroProgressivo(corsoDiStudi);

        this.votoMedio = votoMedio;
        this.corsoDiStudi = corsoDiStudi;
    }

    public Studente(String nome, String cognome, String codiceFiscale, String matricola,
                    float votoMedio, CorsoDiStudi corsoDiStudi) {
        super(nome, cognome, codiceFiscale, matricola);

        this.votoMedio = votoMedio;
        this.corsoDiStudi = corsoDiStudi;

        try {
            int lunghezzaPrefisso = corsoDiStudi.getPrefisso().length();

            String numProgStr = matricola.substring(lunghezzaPrefisso);
            int numProg = Integer.parseInt(numProgStr);
            NumeroProgressivoMatricola.sincronizza(corsoDiStudi, numProg);

        } catch (NumberFormatException e) {
            System.out.println("Errore nel parsing del suffisso della matricola: " + matricola);
        }
    }

    public float getVotoMedio() {
        return votoMedio;
    }

    public CorsoDiStudi getCorsoDiStudi() {
        return corsoDiStudi;
    }

    @Override
    public String toString() {
        return super.toString() + ", Voto Medio: " + votoMedio + ", CdS: " + corsoDiStudi;
    }
}
