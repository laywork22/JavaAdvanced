import java.nio.file.Path;

public class NewMain {
    public static void main(String[] args) {
        //sia percorso che file
        //si può passare una gerarchia intera
        Path p = Path.of("data", "csv", "file.csv");

        System.out.println(p.toAbsolutePath());

        //relative path
        System.out.println(p);


    }
}
