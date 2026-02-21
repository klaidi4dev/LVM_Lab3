package dev.ua._klaidi4_.lmv_lab3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dev.ua._klaidi4_.lmv_lab3.database.SqLite;
import dev.ua._klaidi4_.lmv_lab3.managers.ControllerManager;
import dev.ua._klaidi4_.lmv_lab3.modules.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class RemoveUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane removeAnchorPane;

    @FXML
    private Button removeUserButton;

    @FXML
    private Button removeUserButtonBack;

    @FXML
    private TableView<User> removeUserTable;

    @FXML
    private TableColumn<User, String> removeUserTableColumEmail;

    @FXML
    private TableColumn<User, Integer> removeUserTableColumID;

    @FXML
    private TableColumn<User, String> removeUserTableColumName;

    @FXML
    private TextField removeUserText;

    @FXML
    void initialize() {
        ControllerManager.requestFocus(removeAnchorPane);
        removeUserButtonBack.setOnAction(e -> ControllerManager.backToHome(removeUserButtonBack, "/fxml/main.fxml"));
        removeUserTableColumID.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getId()));
        removeUserTableColumName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        removeUserTableColumEmail.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEmail()));
        ObservableList<User> list = FXCollections.observableArrayList();
        removeUserTable.setItems(list);
        list.clear();
        list.addAll(SqLite.getUsers());

        removeUserText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                removeUserText.setText(newValue.replaceAll("[^\\d]", ""));            }
        });
        removeUserButton.setOnAction(e -> {
           if(SqLite.isUserExists(Integer.parseInt(removeUserText.getText()))) {
               SqLite.removeUser(Integer.parseInt(removeUserText.getText()));
               removeUserText.clear();
               list.clear();
               list.addAll(SqLite.getUsers());
           } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Помилка");
               alert.setHeaderText(null);
               alert.setContentText("Такого id користувача немає");
               alert.showAndWait();
           }
        });

    }

}
