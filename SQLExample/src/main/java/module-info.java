module it.unisa.sqlex {
    requires javafx.controls;
    requires javafx.fxml;

    opens it.unisa.sqlex to javafx.fxml;
    exports it.unisa.sqlex;
}
