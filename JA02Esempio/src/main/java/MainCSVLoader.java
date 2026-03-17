import java.util.Objects;

public class MainCSVLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        TestCSV t = CSVLoader.load("/home/laywork/JavaAdv/JA02Esempio/src/data.csv");

        Objects.requireNonNull(t).printAll();

    }

}
