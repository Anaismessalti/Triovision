package com.example.triovisiongame.controllers;

import com.example.triovisiongame.models.Piece;
import com.example.triovisiongame.utils.TriovisionUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import static com.example.triovisiongame.common.GameConstants.ENABLE_CARD_BG;
import static com.example.triovisiongame.common.GameConstants.SELECTED_CARD_BG;

public class CardController {
    private boolean isSelected = false;
    @FXML
    public Circle circle1;
    public Piece piece1 = new Piece();

    @FXML
    public Circle circle2;
    public Piece piece2 = new Piece();
    @FXML
    public Circle circle3;
    public Piece piece3 = new Piece();

    @FXML
    public GridPane cardGrid;

    public void initializeCard(String circle1Color, String circle2Color, String circle3Color) {
        this.cardGrid.setStyle(ENABLE_CARD_BG);
        this.circle1.setFill(Color.valueOf(circle1Color));
        this.circle2.setFill(Color.valueOf(circle2Color));
        this.circle3.setFill(Color.valueOf(circle3Color));

        piece1.setBoardRow(1);
        piece1.setBoardColumn(2);
        piece1.setC(circle1);
        piece2.setBoardRow(2);
        piece2.setBoardColumn(2);
        piece2.setC(circle2);
        piece3.setBoardRow(3);
        piece3.setBoardColumn(1);
        piece3.setC(circle3);

    }

    @FXML
    public void onCardClick() {
        if (isSelected) {
            TriovisionUtils.showAlert("This card's already played.");
            return;
        }

        HomeController.selectedCard = this;
        cardGrid.setStyle(SELECTED_CARD_BG);
        HomeController.cardsPlayed++;
        isSelected = true;
    }

    public Paint getColorByIndex(int x, int y) {
        if (piece1.getBoardRow() == x && piece1.getBoardColumn() == y) {
            return piece1.getC().getFill();
        }

        if (piece2.getBoardRow() == x && piece2.getBoardColumn() == y) {
            return piece2.getC().getFill();
        }

        if (piece3.getBoardRow() == x && piece3.getBoardColumn() == y) {
            return piece3.getC().getFill();
        }

        return Color.TRANSPARENT;
    }
}