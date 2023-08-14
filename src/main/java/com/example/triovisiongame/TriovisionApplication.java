package com.example.triovisiongame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TriovisionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TriovisionApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Trio-Vision Game by Anais Messalti");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}