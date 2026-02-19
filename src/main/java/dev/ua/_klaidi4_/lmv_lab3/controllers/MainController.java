package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button addBook;

    @FXML
    private Button armorBook;

    @FXML
    private ImageView imageLableBook;

    @FXML
    private Label lableBook;

    @FXML
    private Button registerBook;

    @FXML
    private Button searchBook;

    @FXML
    private Button writingBook;

    @FXML
    void initialize() {
        registerButton(registerBook,"/fxml/singUp.fxml");
        registerButton(addBook, "/fxml/addBook.fxml");
        registerButton(searchBook, "/fxml/searchBook.fxml");
    }
    private void registerButton(Button button, String url) {
        button.setOnAction(e -> {
            ControllerManager.backToHome(button, url);
        });
    }

}
