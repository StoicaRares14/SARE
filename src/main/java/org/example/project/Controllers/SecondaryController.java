package org.example.project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SecondaryController implements Initializable {

    @FXML
    public Button backButton;

    @FXML
    public Label scoreLabel;

    @FXML
    private PrimaryController pc;

    Stage scoreStage = null;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
            setScoreLabel();


    }

    public void CloseScoreWindow() {
        scoreStage = (Stage) scoreLabel.getScene().getWindow();
        scoreStage.close();
    }

    public void setScoreLabel() {

        String score = "-1";

        try {
            File myObj = new File("Score.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                score = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        scoreLabel.setText(score);
    }

    public void switchBack(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();

        try {
            Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Primary.fxml")));
            primaryStage.setTitle("SARE-Benchmark");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


        CloseScoreWindow();
    }


}