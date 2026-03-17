/*Un metodo può essere annotato solo una volta da ciascun annotazione
In questi casi si usano le annotazioni ripetibili
Si deve definire un'annotazione che contiene un array di annotazione (annotazione contenitore)
e un'annotazione @Repeatable*/
public @interface Requisiti {
    Requisito[] value();
}
