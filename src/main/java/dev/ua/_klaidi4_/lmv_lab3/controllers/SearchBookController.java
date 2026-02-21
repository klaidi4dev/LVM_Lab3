package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dev.ua._klaidi4_.lmv_lab3.database.SqLite;
import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import dev.ua._klaidi4_.lmv_lab3.modules.Book;
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

public class SearchBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane searchAnchorPane;

    @FXML
    private Button searchBookButton;

    @FXML
    private Button searchBookButtonBack;

    @FXML
    private TableView<Book> searchBookTable;

    @FXML
    private TableColumn<Book, Integer> searchBookTableColumID;

    @FXML
    private TableColumn<Book, String> searchBookTableColumAuthor;

    @FXML
    private TableColumn<Book, String> searchBookTableColumTitle;

    @FXML
    private TableColumn<Book, Integer> searchBookTableColumYear;

    @FXML
    private TextField searchBookText;
    Book temp = new Book();
    @FXML
    void initialize() {
        ControllerManager.requestFocus(searchAnchorPane);
        searchBookButtonBack.setOnAction(e -> {
            ControllerManager.backToHome(searchBookButtonBack, "/fxml/main.fxml");
        });
        searchBookTableColumID.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getId()));
        searchBookTableColumTitle.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitle()));
        searchBookTableColumAuthor.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getAuthor()));
        searchBookTableColumYear.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getYear()));

        ObservableList<Book> bookList = FXCollections.observableArrayList();
        searchBookTable.setItems(bookList);
        bookList.addAll(SqLite.getAllBooks());
        searchBookButton.setOnAction(e -> {
            if (searchBookText.getText().isEmpty()) {
                bookList.clear();
                bookList.addAll(SqLite.getAllBooks());
            } else {
                bookList.clear();
                bookList.addAll(SqLite.searchBooks(searchBookText.getText()));
            }
        });
    }

}
