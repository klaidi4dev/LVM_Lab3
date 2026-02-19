package dev.ua._klaidi4_.lmv_lab3;

import dev.ua._klaidi4_.lmv_lab3.database.SqLite;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {
    private static Main instance;
    public static void main(String[] args) {
        instance = new Main();
        SqLite.connect();
        SqLite.getUser(1);
        Application.launch(StartApp.class, args);}
    public static class StartApp extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            stage.setTitle("Книжковий каталог");
            stage.setScene(scene);
            stage.show();
        }
    }
    public static Main getInstance() {
        return instance;
    }
}
