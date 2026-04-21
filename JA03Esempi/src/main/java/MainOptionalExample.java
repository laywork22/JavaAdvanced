import java.util.Optional;

public class MainOptionalExample {
    public static void main(String[] args) {
        //Se l'argomento non è inizializzato non funziona
        //Optional<String>  myString = Optional.of(null);

        //ofNullable non prevede eccezioni se non tramite orElse in fase di valutazione dell'espressione
        Optional<String> myString = Optional.ofNullable("Ciao");

        System.out.println(myString.orElse("Ciaooo"));

        //Prima si verifica se è presente un valore nell'optional altrimenti non vengono fatte
        //le operazioni di filtraggio
        Optional<String> myNewString = myString.filter((s) -> s.length() > 3);

        System.out.println(myNewString.orElse("Ciao ciao"));

        //Map
        //Fa una corrispondenza tra il valore dell'optional e quello che voglio restituire
        Optional<String> myMappedString = myString.map(s -> s + " a voi");

        System.out.println(myMappedString.get());

        Optional<String> myFlatMappedString = myString.flatMap(s -> Optional.of(s + " a voi"));

        System.out.println(myFlatMappedString.get());

    }
}
