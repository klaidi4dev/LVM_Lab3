module dev.ua._klaidi4_.lmv_lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jdk.attach;

    opens dev.ua._klaidi4_.lmv_lab3.controllers to javafx.fxml;
    opens dev.ua._klaidi4_.lmv_lab3 to javafx.fxml;
    exports dev.ua._klaidi4_.lmv_lab3;
}