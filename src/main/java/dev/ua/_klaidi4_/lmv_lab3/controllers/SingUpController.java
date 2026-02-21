package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import dev.ua._klaidi4_.lmv_lab3.database.SqLite;
import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import dev.ua._klaidi4_.lmv_lab3.modules.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane singUpAnchorPane;

    @FXML
    private Button singUpButton;

    @FXML
    private TextField singUpEmail;

    @FXML
    private TextField singUpName;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    void initialize() {
        ControllerManager.requestFocus(singUpAnchorPane);
        singUpButton.setOnAction(e -> {
            if(!singUpName.getText().isEmpty() && !singUpEmail.getText().isEmpty()) {
                if(!singUpEmail.getText().endsWith("@gmail.com") || singUpEmail.getText().endsWith("@outlook.com")) {
                    alert.setTitle("Помилка");
                    alert.setHeaderText(null);
                    alert.setContentText("Некоректне закінчення\n Використовуйне @gmail.com або @outlook.com");
                    alert.showAndWait();
                    return;
                }
                if((SqLite.getUser(singUpEmail.getText())) == null) {
                    SqLite.addUser(new User(singUpName.getText(), singUpEmail.getText()));
                    ControllerManager.backToHome(singUpButton, "/fxml/main.fxml");
                } else {
                    alert.setTitle("Помилка");
                    alert.setHeaderText(null);
                    alert.setContentText("Такий користувач вже зареєстрований");
                    alert.showAndWait();
                }
            } else {
                alert.setTitle("Помилка");
                alert.setHeaderText(null);
                alert.setContentText("Рядоки пусті");
                alert.showAndWait();
            }
        });
    }
}
