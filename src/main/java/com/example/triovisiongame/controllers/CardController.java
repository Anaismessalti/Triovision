package com.example.triovisiongame.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {

    @FXML
    public Circle circle1;

    @FXML
    public Circle circle2;

    @FXML
    public Circle circle3;

    @FXML
    public GridPane cardGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}