package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import dev.ua._klaidi4_.lmv_lab3.database.SqLite;
import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import dev.ua._klaidi4_.lmv_lab3.modules.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addBookAuthor;

    @FXML
    private Button addBookButton;

    @FXML
    private TextField addBookName;

    @FXML
    private TextField addBookYear;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    void initialize() {
        addBookYear.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                addBookYear.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        addBookButton.setOnAction(e -> {
            if(!addBookName.getText().isEmpty() && !addBookAuthor.getText().isEmpty() && !addBookYear.getText().isEmpty()) {
                SqLite.addBook(new Book(addBookName.getText(), addBookAuthor.getText(), Integer.parseInt(addBookYear.getText()), true));
                ControllerManager.backToHome(addBookButton, "/fxml/main.fxml");
            } else {
                alert.setTitle("Помилка");
                alert.setHeaderText(null);
                alert.setContentText("Один із рядків пустий");
                alert.showAndWait();
            }
        });
    }
}
