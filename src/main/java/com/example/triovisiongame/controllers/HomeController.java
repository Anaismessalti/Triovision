package com.example.triovisiongame.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.triovisiongame.common.GameConstants.*;

public class HomeController implements Initializable {

    @FXML
    private Pane gamePane;

    @FXML
    private Button btnInstructions;

    @FXML
    private Button btnStartGame;

    @FXML private CardController card01Controller;
    @FXML private CardController card02Controller;
    @FXML private CardController card03Controller;
    @FXML private CardController card04Controller;
    @FXML private CardController card05Controller;
    @FXML private CardController card06Controller;
    @FXML private CardController card07Controller;
    @FXML private CardController card08Controller;
    @FXML private CardController card09Controller;
    @FXML private CardController card10Controller;
    @FXML private CardController card11Controller;
    @FXML private CardController card12Controller;

    @FXML private BoardController boardController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    protected void onBtnStartGameClick () {
        gamePane.setStyle("-fx-background-color: #aad7fa; -fx-border-color: #000000;");
        boardController.generateBoard();
        initializeCards();
    }


    public void initializeCards () {
        card01Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card01Controller.circle1.setFill(Color.valueOf(BLUE));
        card01Controller.circle2.setFill(Color.valueOf(RED));
        card01Controller.circle3.setFill(Color.valueOf(RED));

        card02Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card02Controller.circle1.setFill(Color.valueOf(RED));
        card02Controller.circle2.setFill(Color.valueOf(RED));
        card02Controller.circle3.setFill(Color.valueOf(BLUE));

        card03Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card03Controller.circle1.setFill(Color.valueOf(RED));
        card03Controller.circle2.setFill(Color.valueOf(YELLOW));
        card03Controller.circle3.setFill(Color.valueOf(GREEN));

        card04Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card04Controller.circle1.setFill(Color.valueOf(YELLOW));
        card04Controller.circle2.setFill(Color.valueOf(BLUE));
        card04Controller.circle3.setFill(Color.valueOf(GREEN));

        card05Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card05Controller.circle1.setFill(Color.valueOf(RED));
        card05Controller.circle2.setFill(Color.valueOf(BLUE));
        card05Controller.circle3.setFill(Color.valueOf(GREEN));

        card06Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card06Controller.circle1.setFill(Color.valueOf(RED));
        card06Controller.circle2.setFill(Color.valueOf(RED));
        card06Controller.circle3.setFill(Color.valueOf(RED));

        card07Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card07Controller.circle1.setFill(Color.valueOf(GREEN));
        card07Controller.circle2.setFill(Color.valueOf(YELLOW));
        card07Controller.circle3.setFill(Color.valueOf(YELLOW));

        card08Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card08Controller.circle1.setFill(Color.valueOf(BLUE));
        card08Controller.circle2.setFill(Color.valueOf(BLUE));
        card08Controller.circle3.setFill(Color.valueOf(GREEN));

        card09Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card09Controller.circle1.setFill(Color.valueOf(BLUE));
        card09Controller.circle2.setFill(Color.valueOf(GREEN));
        card09Controller.circle3.setFill(Color.valueOf(GREEN));

        card10Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card10Controller.circle1.setFill(Color.valueOf(RED));
        card10Controller.circle2.setFill(Color.valueOf(YELLOW));
        card10Controller.circle3.setFill(Color.valueOf(GREEN));

        card11Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card11Controller.circle1.setFill(Color.valueOf(GREEN));
        card11Controller.circle2.setFill(Color.valueOf(BLUE));
        card11Controller.circle3.setFill(Color.valueOf(RED));

        card12Controller.cardGrid.setStyle(ENABLE_CARD_BG);
        card12Controller.circle1.setFill(Color.valueOf(YELLOW));
        card12Controller.circle2.setFill(Color.valueOf(RED));
        card12Controller.circle3.setFill(Color.valueOf(GREEN));
    }

    @FXML
    protected void onBtnInstructionsClick () {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TrioVision Game Instructions");
        alert.setContentText("" +
                "Game Rules:" +
                "\n1. You have a 4x4 board with random patterns" +
                "\n2. A set of 12 cards" +
                "\n3. The player who deals all cards in quick time will win" +
                "\n4. You can only jump 1 box to match the pattern." +
                "\n\n" +
                "How to Play:" +
                "\n1. Choose mode [Single Player or Two Player]" +
                "\n2. Click on Start Game button." +
                "\n3. Click on Generate to draw pattern on board" +
                "\n4. Select the card you want to play" +
                "\n5. Make the pattern on board by moving the circles" +
                "\n6. If pattern matches with selected card, you get +1" +
                "\n7. Repeat the same cycle till all cards are dealt" +
                "");
        alert.show();
    }

}