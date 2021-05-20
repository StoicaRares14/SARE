package org.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public Main(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Primary.fxml")));
        primaryStage.setTitle("SARE-Benchmark");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
