package org.unisa;

public class Main {
    public static void main(String[] args) {
        PersonaUnisa p1 = new Docente("Gianluca", "Grigi", "GNLGRG0019", "92441", "eng");
        PersonaUnisa p2 = new Docente("Stefano", "Grigi", "STFGRG003", "93541", "oop");
        PersonaUnisa p3 = new Studente("Mario", "Rossi", "MRRRSS001", 24.8F, CorsoDiStudi.LT);
        PersonaUnisa p5 = new Studente("Luigi", "Salvati", "LGISVT001", "0612708848",27.3F, CorsoDiStudi.LT);
        PersonaUnisa p4 = new Studente("Mario", "Bianchi", "MRRBNC001", 27.3F, CorsoDiStudi.LT);

        Anagrafica a = new Anagrafica();

        a.add(p1);
        a.add(p2);
        a.add(p3);
        a.add(p4);
        a.add(p5);

        System.out.println("=== TEST ANAGRAFICA ===");
        System.out.println(a);

        System.out.println("=== TEST SCRITTURA SU FILE ===");
        a.writeCsv("elenco_unisa.txt");

        System.out.println("=== TEST LETTURA DA FILE ===");
        Anagrafica nuova = Anagrafica.readCSV("elenco_unisa.txt");
        System.out.println(nuova);

    }
}