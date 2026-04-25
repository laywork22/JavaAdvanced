package it.unisa.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerSetupController {
    private boolean confirmed = false;

    @javafx.fxml.FXML
    private Button openChatBtn;
    @javafx.fxml.FXML
    private TextField portNumFld;
    @javafx.fxml.FXML
    private TextField hostNameFld;

    @javafx.fxml.FXML
    public void openChat(ActionEvent actionEvent) {
        if (portNumFld.getText().isEmpty() || hostNameFld.getText().isEmpty()) {
            mostraAlert("Errore di parsing", "I campi non possono essere vuoti");
            return;
        }

        confirmed = true;

        Stage s = (Stage) portNumFld.getScene().getWindow();
        s.close();
    }

    public int getPortNum() {
        return Integer.parseInt(portNumFld.getText());
    }

    public String getHostName() {
        return hostNameFld.getText();
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
