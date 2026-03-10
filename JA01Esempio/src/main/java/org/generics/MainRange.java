package org.generics;

public class MainRange {
    public static void main(String[] args) {
        Range<Integer> r = new Range<>(2, 5);

        System.out.println(r);

        int num = 7;
        System.out.println("Verifico se " + num + " è contenuto:" + r.checkElement(num));

        /*

        Range<String> rs = new Range<>("casa", "edificio");
        String parola = "chiosco";

        System.out.println("Verifico se " + parola + "è contenuto: " + rs.checkElement(parola));*/
    }
}
