package it.unisa.csvframework;

public class Main {

    public static void main(String[] args) {

        TestCSV test = (TestCSV) Manager.loader("src/main/data.csv");

        System.out.println("Studenti:");
        for(Studente s : test.studenti) System.out.println(s);

        System.out.println("\nPersone:");
        for(Persona p : test.persone) System.out.println(p);
    }
}