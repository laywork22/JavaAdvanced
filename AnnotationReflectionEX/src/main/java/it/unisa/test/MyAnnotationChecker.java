package it.unisa.test;

import it.unisa.data.LongClass;
import it.unisa.data.ShortClass;
import it.unisa.exceptions.AnnotationException;
import it.unisa.myannotations.AtMostThree;
import it.unisa.myannotations.FieldNumberConstraint;

import java.lang.reflect.Field;

public class MyAnnotationChecker {
    public static void main(String[] args) {
        LongClass lc = new LongClass();
        ShortClass sc = new ShortClass();

        Class<?> clc = lc.getClass();
        Class<?> csc = sc.getClass();

        try{
            testCheckAtMostThree(csc);
        } catch(AnnotationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" ");

        try{
            testCheckAtMostThree(clc);
        } catch(AnnotationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" ");

        try{

            testFieldNumberConstraint(csc);
        } catch(AnnotationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" ");

        try{
            testFieldNumberConstraint(clc);
        } catch(AnnotationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" ");

    }

    public static void testCheckAtMostThree(Class<?> c) {
        System.out.println("Controllo classe: " + c.getCanonicalName());

        AtMostThree amt = null;

        if ((amt = c.getAnnotation(AtMostThree.class)) != null) {
            //Si esclude il/i costruttore/i(?)
            if (c.getDeclaredMethods().length > 3) {
                throw new AnnotationException("Verifica classe effettuata: la classe presenta un numero di metodi (" + c.getDeclaredMethods().length + ") diverso da tre, costruttori esclusi.");
            } else {
                System.out.println("Verifica classe effettuata: OK!");
            }
        }
    }

    public static void testFieldNumberConstraint(Class <?> c) {
        System.out.println("Controllo vincolo su classe: " + c.getCanonicalName());

        Field[] campi = c.getDeclaredFields();
        FieldNumberConstraint fnc;

        if ((fnc = c.getDeclaredAnnotation(FieldNumberConstraint.class)) != null) {
            if (campi.length == fnc.value()) {
                System.out.println("Controllo effettuato: numero di campi pari a quello dichiarato nell'annotazione");

            } else {
                throw new AnnotationException("Controllo effettuato: Il numero dei campi (" + campi.length + ") è diverso da quello dichiarato nell'annotazione ("+ fnc.value() + ")");
                //System.out.println("Il numero dei campi (" + campi.length + ") è diverso da quello dichiarato nell'annotazione (" + fnc.value() + ")" );
            }
        }
    }
}
