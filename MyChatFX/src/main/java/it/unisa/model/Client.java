package it.unisa.model;

import java.io.Serializable;
import java.util.function.Consumer;

public class Client extends NetworkConnection{
    private final String hostName;

    public Client(String ip, int port, Consumer<Serializable> onReceive, String hostName) {
        super(ip, port, onReceive);

        this.hostName = hostName;
    }

    public Client(String ip, int port, Consumer<Serializable> onReceive) {
        super(ip, port, onReceive);

        this.hostName = "Client";
    }

    public String getHostName() {
        return hostName;
    }

    @Override
    public boolean isServer() {
        return false;
    }
}
