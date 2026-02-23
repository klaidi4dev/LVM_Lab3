package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private ImageView ImageBook;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button addBook;

    @FXML
    private Button armorBook;

    @FXML
    private Label lableBook;

    @FXML
    private Button registerBook;

    @FXML
    private Button searchBook;

    @FXML
    private Button writingBook;

    @FXML
    private Button removeUser;

    @FXML
    void initialize() {
        ImageBook.setImage(new Image(getClass().getResourceAsStream("/assets/logo.png")));
        ControllerManager.requestFocus(anchorPane);
        registerButton(registerBook,"/fxml/singUp.fxml");
        registerButton(addBook, "/fxml/addBook.fxml");
        registerButton(searchBook, "/fxml/searchBook.fxml");
        registerButton(writingBook, "/fxml/removeBook.fxml");
        registerButton(armorBook, "/fxml/armorBook.fxml");
        registerButton(removeUser, "/fxml/removeUser.fxml");
    }
    private void registerButton(Button button, String url) {
        button.setOnAction(e -> {
            ControllerManager.backToHome(button, url);
        });
    }

}
