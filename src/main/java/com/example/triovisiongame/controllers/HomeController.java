package com.example.triovisiongame.controllers;

import com.example.triovisiongame.utils.TriovisionUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import static com.example.triovisiongame.common.GameConstants.*;

public class HomeController {
    public static boolean isGameStarted = false, isGameCompleted = false, isMultiPlayer = false, isPlayerSelected = false;
    public static int cardsPlayed = 0;
    public static int p1Score = 0, p2Score = 0;
    public static CardController selectedCard;

    @FXML
    private Pane gamePane;
    @FXML
    private Button btnInstructions;

    @FXML
    private Button btnPlayer1;

    @FXML
    private Button btnPlayer2;

    @FXML
    private Button btnStartGame;

    @FXML
    private Label p1ScoreLabel;

    @FXML
    private Label p2ScoreLabel;

    @FXML
    public Label p1ScoreValue;

    @FXML
    public Label p2ScoreValue;
    @FXML
    private CardController card01Controller;
    @FXML
    private CardController card02Controller;
    @FXML
    private CardController card03Controller;
    @FXML
    private CardController card04Controller;
    @FXML
    private CardController card05Controller;
    @FXML
    private CardController card06Controller;
    @FXML
    private CardController card07Controller;
    @FXML
    private CardController card08Controller;
    @FXML
    private CardController card09Controller;
    @FXML
    private CardController card10Controller;
    @FXML
    private CardController card11Controller;
    @FXML
    private CardController card12Controller;

    @FXML
    private BoardController boardController;


    @FXML
    protected void onBtnStartGameClick() {
        if(!isPlayerSelected) {
            TriovisionUtils.showAlert("Please selected player 1 or player 2 to start game.");
            return;
        }

        isGameStarted = true;
        btnStartGame.setDisable(true);
        gamePane.setStyle("-fx-background-color: #aad7fa; -fx-border-color: #000000;");
        boardController.initializeBoard();
        initializeCards();
    }

    @FXML
    protected void onBtnGenerateClick() {
        if (isGameStarted) {
            boardController.generateBoard();
        } else {
            TriovisionUtils.showAlert("Please start the game first. Thanks.");
        }
    }

    public void initializeCards() {
        card01Controller.initializeCard(BLUE, RED, RED);
        card02Controller.initializeCard(RED, RED, BLUE);
        card03Controller.initializeCard(RED, YELLOW, GREEN);
        card04Controller.initializeCard(YELLOW, BLUE, GREEN);
        card05Controller.initializeCard(RED, BLUE, GREEN);
        card06Controller.initializeCard(RED, RED, GREEN);
        card07Controller.initializeCard(GREEN, YELLOW, YELLOW);
        card08Controller.initializeCard(BLUE, BLUE, GREEN);
        card09Controller.initializeCard(BLUE, GREEN, GREEN);
        card10Controller.initializeCard(RED, YELLOW, GREEN);
        card11Controller.initializeCard(GREEN, BLUE, RED);
        card12Controller.initializeCard(YELLOW, RED, GREEN);
    }

    @FXML
    protected void onBtnInstructionsClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TrioVision Game Instructions");
        alert.setContentText("" +
                "Game Rules:" +
                "\n1. You have a 4x4 board with random patterns" +
                "\n2. A set of 12 cards" +
                "\n3. The player who deals all cards in quick time will win" +
                "\n4. You can only jump 1 box to match the pattern." +
                "\n4. The pattern is matched in only vertical orientation." +
                "\n\n" +
                "How to Play:" +
                "\n1. Select mode [Single Player or Two Player]" +
                "\n2. Press Start Game button." +
                "\n3. Press Generate to draw pattern on board" +
                "\n4. Select the card you want to play" +
                "\n5. Make the pattern on board by moving the piece" +
                "\n6. If pattern matches with selected card, you get +1" +
                "\n7. To see scores, Press Announce Result button" +
                "");
        alert.show();
    }


    @FXML
    protected void onBtnPlayer1Click(ActionEvent actionEvent) {
        isPlayerSelected = true;
        p1ScoreLabel.setVisible(true);
        btnPlayer1.setStyle(SELECTED_CARD_BG);
        disablePlayerButtons();
    }

    @FXML
    protected void onBtnPlayer2Click(ActionEvent actionEvent) {
        isPlayerSelected = true;
        p2ScoreLabel.setVisible(true);
        disablePlayerButtons();
    }

    private void disablePlayerButtons() {
        btnPlayer1.setDisable(true);
        btnPlayer2.setDisable(true);
    }

}