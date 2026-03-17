import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * Reflection API -> strumento di ispezione
 * Data una classe di partenza è capace di risalire a tutti gli attributi della classe stessa.
 * <p>
 * Recuperare un oggetto Class da un tipo specifico
 *
 * @author laywork
 */
public class Main {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Studente s = new Studente("Mario", "Rossi", "MRS001", "06127001");

        //Ottenere un tipo Class
        Class<?> c = s.getClass();

        Class<?> c1 = Studente.class;

        Class<?> c2 = Class.forName("Studente");

        inspectClass(c);
        //checkModifiers();
    }

    public static void checkModifiers() {
        //16 bit per modificatore, ogni bit ha una particolare informazione
        //Con un solo numero posso avere più modificatori
        //Il numero è la somma dei vari modificatori presenti
        /*
         * i: 1 public
         * i: 2 private
         * i: 4 protected
         * i: 8 static
         * i: 16 final
         * i: 32 synchronized
         * etc.
         */
        //Es public static = 1 + 8 = 9

        for (int i = 1; i < 10; i++) {
            System.out.println("i: " + i + " " + Modifier.toString(i));
        }
    }

    public static void inspectClass(Class<?> c) {
        //CanonicalName e Name sono molto simili e danno
        //una descrizione della classe a partire dal package

        System.out.println("Classe: " + c.getCanonicalName());

        Constructor<?>[] cs = c.getConstructors();

        //ottenere la firma di un costruttore
        for (Constructor<?> cc : cs) {
            StringBuilder sb = new StringBuilder();

            //I modificatori sono codificati come una bit mask
            //segue il nome del costruttore
            //con SimpleName evito la parte relativa al package
            sb.append(Modifier.toString(cc.getModifiers())).append(' ').append(cc.getDeclaringClass().getSimpleName()).append('(');

            //Otteniamo i parametri in un array
            Parameter[] p = cc.getParameters();

            for (Parameter pc : p) {
                sb.append(pc.getType().getSimpleName()).append(' ').
                        append(pc.getName()).append(",");
            }

            sb.setCharAt(sb.length() - 1, ')');

            System.out.println(sb);

            System.out.println("***Metodo***");

            Method[] m = c.getMethods();
            //Method[] m1 = c.getDeclaredMethod(...);
            //Fa vedere tutti i metodi dichiarati nella classe compresi quelli privati
            for(Method mc : m) {
                //public void metodo(String nome, String cognome, String codiceFiscale, String matricola)
                StringBuilder s1 = new StringBuilder();

                s1.append(Modifier.toString(mc.getModifiers())).append(' ').append(mc.getReturnType().getSimpleName()).
                        append(' ');

                s1.append(mc.getName()).append("( ");

                Parameter[] pm = mc.getParameters();

                for (Parameter pc : pm) {
                    s1.append(pc.getType().getSimpleName()).append(' ').
                            append(pc.getName()).append(',');
                }

                s1.setCharAt(s1.length() - 1, ')');

                System.out.println(s1);

                //Gestione annotazioni
                DaCompletare dc = null;

                //Se il metodo è annotato allora è un'istanza diversa da null
                //Le annotazioni possono essere annotate da altre annotazioni
                //Ogni annotazione ha una retention (validità) può essere inclusa nel file .class ma non essere
                //processata, visibile a tempo di compilazione o eliminata dal compilatore.

                /*
                 * Si usano meta annotazioni come Target, Retention, Documented etc.
                 *
                 * Sono presenti tre retention policy:
                 *  Source -> L'annotazione è eliminata dal compilatore
                 *  Class -> Conservata nel .class ma ignorata dalla JVM (non si può usare la Reflection)
                 *  Runtime -> Conservata nel file *.class e letta dalla JVM
                 */
                if ((dc = mc.getAnnotation(DaCompletare.class)) != null) {
                    System.out.println(dc);
                    System.out.println("***Annotazioni***");
                    System.out.println("DaCompletare assegnato a: " + dc.assegnatoA() + " entro: " + dc.entroData());
                }

                Requisito[] r = mc.getAnnotationsByType(Requisito.class);

                System.out.println("DImensione annotazioni: " + r.length);

                for (Requisito rc : r) {
                    System.out.println("***Annotazioni ripetute e marcatrici***");
                    System.out.println("---> Requisito: " + rc.value());
                }
            }


        }

    }

}
