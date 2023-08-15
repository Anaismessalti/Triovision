package com.example.triovisiongame.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.triovisiongame.common.GameConstants.WHITE;

public class BoardController implements Initializable {

    @FXML
    public Circle circle1;

    @FXML
    public Circle circle2;

    @FXML
    public Circle circle3;

    @FXML
    public Circle circle4;

    @FXML
    public Circle circle5;

    @FXML
    public Circle circle6;

    @FXML
    public Circle circle7;

    @FXML
    public Circle circle8;

    @FXML
    public Circle circle9;

    @FXML
    public Circle circle10;

    @FXML
    public Circle circle11;

    @FXML
    public Circle circle12;
    @FXML
    public Circle circle13;

    @FXML
    public Circle circle14;

    @FXML
    public Circle circle15;

    @FXML
    public Circle circle16;

    @FXML
    public GridPane boardGrid;

    @FXML
    public Pane boardPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void generateBoard() {
        this.boardPane.setStyle("-fx-background-color: #aad7fa; -fx-border-color: #000000; -fx-border-width: 2px 2px 2px 2px;");
        this.circle1.setFill(Color.valueOf(WHITE));
        this.circle4.setFill(Color.valueOf(WHITE));
        this.circle6.setFill(Color.valueOf(WHITE));
        this.circle7.setFill(Color.valueOf(WHITE));
        this.circle10.setFill(Color.valueOf(WHITE));
        this.circle11.setFill(Color.valueOf(WHITE));
        this.circle13.setFill(Color.valueOf(WHITE));
        this.circle16.setFill(Color.valueOf(WHITE));
    }
}