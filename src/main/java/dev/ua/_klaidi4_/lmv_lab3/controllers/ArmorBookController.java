package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dev.ua._klaidi4_.lmv_lab3.database.SqLite;
import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import dev.ua._klaidi4_.lmv_lab3.modules.Book;
import dev.ua._klaidi4_.lmv_lab3.modules.Reservations;
import dev.ua._klaidi4_.lmv_lab3.modules.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ArmorBookController {
    private static Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane armorAnchorPane;

    @FXML
    private Button armorBookButton;

    @FXML
    private Button armorBookButtonRemove;

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
    private TableView<Reservations> armorTable;

    @FXML
    private TableColumn<Reservations, String> armorTableColumBookAuthor;

    @FXML
    private TableColumn<Reservations, Integer> armorTableColumBookID;

    @FXML
    private TableColumn<Reservations, String> armorTableColumBookTitle;

    @FXML
    private TableColumn<Reservations, Integer> armorTableColumBookYear;

    @FXML
    private TableColumn<Reservations, String> armorTableColumUserEmail;

    @FXML
    private TableColumn<Reservations, Integer> armorTableColumUserID;

    @FXML
    private TableColumn<Reservations, String> armorTableColumUserName;
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

        armorTableColumBookID.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getBook_id().getId()));
        armorTableColumBookTitle.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBook_id().getTitle()));
        armorTableColumBookAuthor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBook_id().getAuthor()));
        armorTableColumBookYear.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getBook_id().getYear()));
        armorTableColumUserID.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getUser_id().getId()));
        armorTableColumUserEmail.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUser_id().getEmail()));
        armorTableColumUserName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUser_id().getName()));
        ObservableList<Reservations> reservationsList = FXCollections.observableArrayList();
        armorTable.setItems(reservationsList);
        reservationsList.clear();
        reservationsList.addAll(SqLite.getReservations());

        armorBookButton.setOnAction(e -> {
            if (!armorBookText.getText().isEmpty() && !armorUserText.getText().isEmpty()) {
                   if (SqLite.isBookExists(Integer.parseInt(armorBookText.getText())) && SqLite.isUserExists(Integer.parseInt(armorUserText.getText()))) {
                       if (SqLite.getBook(Integer.parseInt(armorBookText.getText())).isAvailable()) {
                           SqLite.addReservations(new Reservations(new User(Integer.parseInt(armorUserText.getText())), new Book(Integer.parseInt(armorBookText.getText()))));
                           System.out.println("Книга заброньована" + armorUserText.getText() + " " + armorBookText.getText());
                           reservationsList.clear();
                           reservationsList.addAll(SqLite.getReservations());
                           armorBookText.clear();
                           armorUserText.clear();
                       } else {
                           alert.setTitle("Помилка");
                           alert.setHeaderText(null);
                           alert.setContentText("Ця книга уже заброньована");
                           alert.showAndWait();
                           armorBookText.clear();
                           armorUserText.clear();
                       }
                   } else {
                       alert.setTitle("Помилка");
                       alert.setHeaderText(null);
                       alert.setContentText("Такого id користувача або id книги немає");
                       alert.showAndWait();
                       armorBookText.clear();
                       armorUserText.clear();
                   }
            } else {
                alert.setTitle("Помилка");
                alert.setHeaderText(null);
                alert.setContentText("Поля бронювання пусті");
                alert.showAndWait();
            }
        });
        armorBookButtonRemove.setOnAction(e -> {
            if (!armorBookText.getText().isEmpty() && !armorUserText.getText().isEmpty()) {
                if (SqLite.isBookExists(Integer.parseInt(armorBookText.getText())) && SqLite.isUserExists(Integer.parseInt(armorUserText.getText()))) {
                    SqLite.removeReservations(new Reservations(new User(Integer.parseInt(armorUserText.getText())), new Book(Integer.parseInt(armorBookText.getText()))));
                    System.out.println("Книга розброньована " + armorUserText.getText() + " " + armorBookText.getText());
                    reservationsList.clear();
                    reservationsList.addAll(SqLite.getReservations());
                    armorBookText.clear();
                    armorUserText.clear();
                } else {
                    alert.setTitle("Помилка");
                    alert.setHeaderText(null);
                    alert.setContentText("Такого id користувача або id книги немає");
                    alert.showAndWait();
                    armorBookText.clear();
                    armorUserText.clear();
                }
            } else {
                alert.setTitle("Помилка");
                alert.setHeaderText(null);
                alert.setContentText("Поля бронювання пусті");
                alert.showAndWait();
            }
        });

    }
}
