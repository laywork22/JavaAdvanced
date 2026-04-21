import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server s = new Server(54333, System.out::println);

        Client c = new Client("127.0.0.1", 54333, System.out::println);

        s.connect();

        Thread.sleep(2000);

        c.connect();

        Thread.sleep(2000);

        c.send("Ciao Server");

        Thread.sleep(2000);

        c.send("Arrivederci");

        Thread.sleep(2000);

        c.disconnect();
        s.disconnect();
    }
}
