package com.example.triovisiongame.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.example.triovisiongame.common.GameConstants.ENABLE_CARD_BG;

public class CardController {

    @FXML
    public Circle circle1;

    @FXML
    public Circle circle2;

    @FXML
    public Circle circle3;

    @FXML
    public GridPane cardGrid;

    public void initializeCard (String circle1Color, String circle2Color, String circle3Color) {
        this.cardGrid.setStyle(ENABLE_CARD_BG);
        this.circle1.setFill(Color.valueOf(circle1Color));
        this.circle2.setFill(Color.valueOf(circle2Color));
        this.circle3.setFill(Color.valueOf(circle3Color));
    }
}