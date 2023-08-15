package com.example.triovisiongame.controllers;

import com.example.triovisiongame.utils.TriovisionUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Objects;

import static com.example.triovisiongame.common.GameConstants.CIRCLE;

public class BoardController {

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


    public void initializeBoard() {
        hideNodes();
        this.boardPane.setStyle("-fx-background-color: #aad7fa; -fx-border-color: #000000; -fx-border-width: 2px 2px 2px 2px;");
        generateBoard();
    }

    public void generateBoard() {
        if(!HomeController.isGameStarted) return;

        hideNodes();
        List<Integer> boardsToShow = TriovisionUtils.getRandomNumbers();
        for (Integer number : boardsToShow) {
            String circleToShow = CIRCLE + number;
            showNodes(circleToShow);
        }
    }

    public void showNodes(String circleToShow) {
        ObservableList<Node> circles = boardGrid.getChildren();
        for (Node node : circles) {
            if(Objects.isNull(node) || Objects.isNull(node.getId())) continue;

            if (node.getId().equals(circleToShow)) {
                node.setVisible(true);
            }
        }
    }

    public void hideNodes () {
        ObservableList<Node> circles = boardGrid.getChildren();
        for (Node node : circles) {
            if(Objects.isNull(node) || Objects.isNull(node.getId())) continue;
            node.setVisible(false);
        }
    }
}