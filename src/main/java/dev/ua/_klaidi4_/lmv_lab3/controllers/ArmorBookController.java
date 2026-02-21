package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dev.ua._klaidi4_.lmv_lab3.database.SqLite;
import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import dev.ua._klaidi4_.lmv_lab3.modules.Book;
import dev.ua._klaidi4_.lmv_lab3.modules.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ArmorBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane armorAnchorPane;

    @FXML
    private Button armorBookButton;

    @FXML
    private Button armorBookButtonBack;

    @FXML
    private TableView<Book> armorBookTable;

    @FXML
    private TableColumn<Book, String> armorBookTableColumAuthor;

    @FXML
    private TableColumn<Book, Integer> armorBookTableColumID;

    @FXML
    private TableColumn<Book, String> armorBookTableColumTitle;

    @FXML
    private TableColumn<Book, Integer> armorBookTableColumYear;

    @FXML
    private TextField armorBookText;

    @FXML
    private TableView<?> armorTable;

    @FXML
    private TableColumn<?, ?> armorTableColumBookAuthor;

    @FXML
    private TableColumn<?, ?> armorTableColumBookID;

    @FXML
    private TableColumn<?, ?> armorTableColumBookTitle;

    @FXML
    private TableColumn<?, ?> armorTableColumBookYear;

    @FXML
    private TableColumn<?, ?> armorTableColumUserEmail;

    @FXML
    private TableColumn<?, ?> armorTableColumUserID;

    @FXML
    private TableView<User> armorUserTable;

    @FXML
    private TableColumn<User, String> armorUserTableColumEmail;

    @FXML
    private TableColumn<User, Integer> armorUserTableColumID;

    @FXML
    private TableColumn<User, String> armorUserTableColumName;

    @FXML
    private TextField armorUserText;

    @FXML
    void initialize() {
        ControllerManager.requestFocus(armorAnchorPane);
        armorBookButtonBack.setOnAction(e -> {
            ControllerManager.backToHome(armorBookButtonBack, "/fxml/main.fxml");
        });
        armorBookText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                armorBookText.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        armorUserText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                armorUserText.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        armorUserTableColumID.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getId()));
        armorUserTableColumName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        armorUserTableColumEmail.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEmail()));
        ObservableList<User> usersList = FXCollections.observableArrayList();
        armorUserTable.setItems(usersList);
        usersList.clear();
        usersList.addAll(SqLite.getUsers());
        armorBookTableColumID.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getId()));
        armorBookTableColumTitle.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitle()));
        armorBookTableColumAuthor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAuthor()));
        armorBookTableColumYear.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getYear()));
        ObservableList<Book> booksList = FXCollections.observableArrayList();
        armorBookTable.setItems(booksList);
        booksList.clear();
        booksList.addAll(SqLite.getAllBooks());
    }
}
