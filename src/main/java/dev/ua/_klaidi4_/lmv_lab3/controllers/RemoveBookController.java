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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class RemoveBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane removeAnchorPane;

    @FXML
    private Button removeBookButton;

    @FXML
    private Button removeBookButtonBack;

    @FXML
    private TableView<Book> removeBookTable;

    @FXML
    private TableColumn<Book, String> removeBookTableColumAuthor;

    @FXML
    private TableColumn<Book, Integer> removeBookTableColumID;

    @FXML
    private TableColumn<Book, String> removeBookTableColumTitle;

    @FXML
    private TableColumn<Book, Integer> removeBookTableColumYear;

    @FXML
    private TextField removeBookText;
    Book temp = new Book();
    @FXML
    void initialize() {
        ControllerManager.requestFocus(removeAnchorPane);
        removeBookButtonBack.setOnAction(e -> {
            ControllerManager.backToHome(removeBookButtonBack, "/fxml/main.fxml");
        });
        removeBookTableColumID.setCellValueFactory(cellDate ->
                new SimpleObjectProperty<>(cellDate.getValue().getId()));
        removeBookTableColumTitle.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitle()));
        removeBookTableColumAuthor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAuthor()));
        removeBookTableColumYear.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getYear()));

        ObservableList<Book> bookList = FXCollections.observableArrayList();
        removeBookTable.setItems(bookList);
        bookList.clear();
        bookList.addAll(SqLite.getAllBooks());

        removeBookText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                removeBookText.setText(newValue.replaceAll("[^\\d]", ""));
            }
            removeBookButton.setOnAction(e -> {
                if (!SqLite.isReservationBookExists(Integer.parseInt(removeBookText.getText()))) {
                    if (SqLite.isBookExists(Integer.parseInt(removeBookText.getText()))) {
                        SqLite.removeBook(Integer.parseInt(removeBookText.getText()));
                        removeBookText.clear();
                        bookList.clear();
                        bookList.addAll(SqLite.getAllBooks());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Помилка");
                        alert.setHeaderText(null);
                        alert.setContentText("Такого id книги немає");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Помилка");
                    alert.setHeaderText(null);
                    alert.setContentText("Ця книга заброньована, знініміть броню щоб видалити книгу.");
                    alert.showAndWait();
                }
            });
        });
    }

}
