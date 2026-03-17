import jdk.jfr.Registered;

import java.lang.annotation.Repeatable;

//Annotazione marcatrice: ha un solo un valore come parametro che si chiama "value".
//Posso anche non specificare il parametro a cui assegno il valore quindi
//@Requisito("ciao")
@Repeatable(Requisiti.class)
public @interface Requisito {
    String value();
}
