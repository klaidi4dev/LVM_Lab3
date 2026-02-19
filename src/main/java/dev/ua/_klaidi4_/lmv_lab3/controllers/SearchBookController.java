package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SearchBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button searchBookButton;

    @FXML
    private Button searchBookButtonBack;

    @FXML
    private TableView<?> searchBookTable;

    @FXML
    private TableColumn<?, ?> searchBookTableColumAuthor;

    @FXML
    private TableColumn<?, ?> searchBookTableColumTitle;

    @FXML
    private TableColumn<?, ?> searchBookTableColumYear;

    @FXML
    private TextField searchBookText;

    @FXML
    void initialize() {
        searchBookButtonBack.setOnAction(e -> {
            ControllerManager.backToHome(searchBookButtonBack, "/fxml/main.fxml");
        });
    }

}
