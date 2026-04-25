package it.unisa.model;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

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

    class ConnectionThread extends Service<Void> {
        Socket s;
        ObjectOutputStream oos;

        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws Exception {
                    try(Socket s = isServer() ? new ServerSocket(port).accept() : new Socket(ip, port);
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(s.getInputStream())) {

                        ConnectionThread.this.s = s;
                        ConnectionThread.this.oos = oos;

                        while(true) {
                            Serializable msg = (Serializable)  ois.readObject();

                            Platform.runLater(() -> onReceive.accept(msg));
                        }

                    } catch (IOException e) {
                        System.out.println("Connessione chiusa: " + e.getMessage());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    return null;
                }
            };
        }
    }
}

