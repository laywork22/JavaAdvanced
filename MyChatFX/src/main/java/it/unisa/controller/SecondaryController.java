package it.unisa.controller;

import java.io.IOException;

import it.unisa.model.NetworkConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SecondaryController {
    private NetworkConnection connection;

    @FXML
    private TextArea chatArea;
    @FXML
    private TextField textInputBar;

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
        String msg = textInputBar.getText();

        try {
            connection.send(msg);
            appendMessage(msg);
            textInputBar.clear();
        } catch (IOException e) {
           mostraAlert("Errore di connessione", e.getMessage());
        }

    }

    public void setNetworkConnection(NetworkConnection nc) {
        this.connection = nc;
    }

    public void appendMessage(String msg) {
        chatArea.appendText(msg + "\n");
    }

    public void mostraAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);

        a.setTitle(title);
        a.setHeaderText(title);
        a.setContentText(msg);

        a.showAndWait();
    }

}