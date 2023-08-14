package com.example.triovisiongame.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Button btnInstructions;

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