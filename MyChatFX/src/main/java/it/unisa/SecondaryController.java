package it.unisa;

import java.io.IOException;
import java.io.Serializable;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SecondaryController {
    private String ip;
    private int port;
    private Consumer<Serializable> onReceive;

    @FXML
    private TextArea chatArea;
    @FXML
    private Button sendBtn;
    @FXML
    private TextField textInputBar;

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
    }
}