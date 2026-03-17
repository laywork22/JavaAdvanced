    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;

    //Meta-Annotazione: Determina fino a che livello viene conservata l'annotazione e da chi può essere utilizzata
    @Retention(RetentionPolicy.RUNTIME)
    //Meta-Annotazione: Specifica la validità dell'annotazione annotata
    @Target(ElementType.METHOD)
    public @interface DaCompletare {
        String assegnatoA() default "Luigi Rossi";
        String entroData();
    }
