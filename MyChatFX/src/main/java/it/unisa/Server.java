package it.unisa;

import java.io.Serializable;
import java.util.function.Consumer;

public class Server extends NetworkConnection{
    public Server(int port, Consumer<Serializable> onReceive) {
        super(null, port, onReceive);
    }

    @Override
    public boolean isServer() {
        return true;
    }
}
