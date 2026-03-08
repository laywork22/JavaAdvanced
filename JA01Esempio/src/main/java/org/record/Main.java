package org.record;

public class Main {
    public static void main(String[] args) {
        Studente s = new Studente("Mario", "Rossi", "0612709843");
        System.out.println(s);
        Studente s1 = new Studente("Luigi", "Rossi");
        System.out.println(s1);
    }
}
