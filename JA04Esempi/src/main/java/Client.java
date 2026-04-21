import java.io.Serializable;
import java.util.function.Consumer;

public class Client extends NetworkConnection{
    public Client(String ip, int port, Consumer<Serializable> onReceive ) {
        super(ip, port, onReceive);
    }

    @Override
    public boolean isServer() {
        return false;
    }
}
