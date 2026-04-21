package it.unisa;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {
    private String ip;
    private int port;
    private ConnectionThread connection;
    private Consumer<Serializable> onReceive;

    public NetworkConnection(String ip, int port, Consumer<Serializable> onReceive) {
        this.ip = ip;
        this.port = port;

        this.connection = new ConnectionThread();
        this.onReceive = onReceive;
    }

    public void connect() {
        connection.start();
    }

    public void disconnect() throws IOException {
        connection.s.close();
    }

    //Vogliamo che il messaggio sia un flusso di byte
    public void send(Serializable data) throws IOException {
        connection.oos.writeObject(data);
    }

    public abstract boolean isServer();

    class ConnectionThread extends Thread {
        Socket s;
        ObjectOutputStream oos;

        @Override
        public void run() {
            try(Socket s = isServer() ? new ServerSocket(port).accept() : new Socket(ip, port);
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream())) {

                this.s = s;
                this.oos = oos;

                while(true) {
                    Serializable msg = (Serializable) ois.readObject();
                    onReceive.accept(msg);
                }
            } catch (IOException e) {
                System.out.println("Connessione chiusa");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}

