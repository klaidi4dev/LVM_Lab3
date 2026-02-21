package dev.ua._klaidi4_.lmv_lab3;

import dev.ua._klaidi4_.lmv_lab3.database.SqLite;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {
    private static Main instance;
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 600;
    public static void main(String[] args) {
        instance = new Main();
        SqLite.connect();
        Application.launch(StartApp.class, args);}
    public static class StartApp extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            stage.setTitle("Книжковий каталог");
            stage.setFullScreen(false);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public static Main getInstance() {
        return instance;
    }
}
