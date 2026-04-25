package it.unisa.controller;

import java.io.IOException;

import it.unisa.App;
import it.unisa.model.Client;
import it.unisa.model.NetworkConnection;
import it.unisa.model.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button noBtn;
    @FXML
    private Button yesBtn;

    @FXML
    public void serverSideSwitch(ActionEvent actionEvent) throws IOException {
        avviaConnessione(true);
    }

    @FXML
    public void clientSideSwitch(ActionEvent actionEvent) throws IOException {
        avviaConnessione(false);
    }

    private void avviaConnessione(boolean isServer) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController sc = loader.getController();

        NetworkConnection nc;

        if (isServer) {
            FXMLLoader l = new FXMLLoader(App.class.getResource("serverConnectionSetup.fxml"));
            Parent rs = l.load();
            ServerSetupController st = l.getController();

            Stage setupStage = new Stage();
            setupStage.setScene(new Scene(rs));
            setupStage.setTitle("Setup Connessione Client");
            setupStage.initModality(Modality.APPLICATION_MODAL);

            setupStage.showAndWait();

            if (!st.isConfirmed()) {
                return;
            }

            nc = new Server(st.getPortNum(), data -> {
                sc.appendMessage("Client: " + (String) data);
            });
        }
        else {
            FXMLLoader l = new FXMLLoader(App.class.getResource("clientConnectionSetup.fxml"));
            Parent r = l.load();
            ClientSetupController st = l.getController();

            Stage setupStage = new Stage();
            setupStage.setScene(new Scene(r));
            setupStage.setTitle("Setup Connessione Client");
            setupStage.initModality(Modality.APPLICATION_MODAL);

            setupStage.showAndWait();

            if (!st.isConfirmed()) {
                return;
            }

            nc = new Client(st.getIp(), st.getPortNum(), data -> {
               sc.appendMessage("Server: " + (String) data );
            });
        }

        sc.setNetworkConnection(nc);

        nc.connect();

        Stage s = (Stage) yesBtn.getScene().getWindow();

        s.setOnCloseRequest(e -> {
            try {
                if (nc != null) {
                    nc.disconnect();
                }
            } catch (IOException ex) {
                mostraAlert("Errore connessione", ex.getMessage());
            }
        });

        yesBtn.getScene().setRoot(root);
    }

    public void mostraAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);

        a.setTitle(title);
        a.setHeaderText(title);
        a.setContentText(msg);

        a.showAndWait();
    }

}
