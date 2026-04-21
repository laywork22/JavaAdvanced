package it.unisa;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button noBtn;
    @FXML
    private Button yesBtn;

    @FXML
    public void serverSideSwitch(ActionEvent actionEvent) throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void clientSideSwitch(ActionEvent actionEvent) throws IOException {
        App.setRoot("secondary");
    }
}
