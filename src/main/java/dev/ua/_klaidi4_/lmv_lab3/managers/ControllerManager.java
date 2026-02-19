package dev.ua._klaidi4_.lmv_lab3.managers;

import dev.ua._klaidi4_.lmv_lab3.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControllerManager {
    public static void backToHome(Button button, String url) {
        FXMLLoader loader = new FXMLLoader(Main.getInstance().getClass().getResource(url));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = button.getScene();
        scene.setRoot(loader.getRoot());
    }
}
