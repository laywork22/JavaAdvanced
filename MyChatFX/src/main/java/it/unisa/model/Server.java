package it.unisa.model;

import java.io.Serializable;
import java.util.function.Consumer;

public class Server extends NetworkConnection{
    private final String hostName;

    public Server(int port, Consumer<Serializable> onReceive, String hostName) {
        super(null, port, onReceive);

        this.hostName = hostName;
    }


    public Server(int port, Consumer<Serializable> onReceive) {
        super(null, port, onReceive);

        this.hostName = "Server";
    }

    public String getHostName() {
        return hostName;
    }

    @Override
    public boolean isServer() {
        return true;
    }
}
