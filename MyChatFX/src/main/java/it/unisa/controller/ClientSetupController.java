package it.unisa.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ClientSetupController {
    private boolean confirmed = false;

    @javafx.fxml.FXML
    private TextField ipAddrFld;
    @javafx.fxml.FXML
    private Button openChatBtn;
    @javafx.fxml.FXML
    private TextField portNumFld;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void openChat(ActionEvent actionEvent) throws IOException {
        if (ipAddrFld.getText().isEmpty() || portNumFld.getText().isEmpty()) {
            mostraAlert("Errore di parsing", "I campi non possono essere vuoti");
            return;
        }

        confirmed = true;

        Stage s = (Stage) ipAddrFld.getScene().getWindow();
        s.close();
    }

    public String getIp() {
        return ipAddrFld.getText();
    }

    public int getPortNum() {
        return Integer.parseInt(portNumFld.getText());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void mostraAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);

        a.setTitle(title);
        a.setHeaderText(title);
        a.setContentText(msg);

        a.showAndWait();
    }
}