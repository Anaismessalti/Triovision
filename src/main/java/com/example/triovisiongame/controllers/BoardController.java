package com.example.triovisiongame.controllers;

import com.example.triovisiongame.enums.BoardColors;
import com.example.triovisiongame.models.Piece;
import com.example.triovisiongame.utils.TriovisionUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;

import static com.example.triovisiongame.common.GameConstants.*;

public class BoardController implements Initializable {
    private List<Piece> pieces;

    @FXML
    public Pane boardPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pieces = new ArrayList<>();
        addRectangles();
        addCircles();
        hideNodes();
    }

    public void initializeBoard() {
        boardPane.setStyle("-fx-background-color: #aad7fa; -fx-border-color: #000000; -fx-border-width: 2px 2px 2px 2px;");
        generateBoard();
        boardPane.getChildren().forEach(node -> {
            if(Objects.isNull(node.getId())) {
                Rectangle r = (Rectangle) node;
                r.setFill(Color.valueOf(ENABLE_BOARD_COLOR));
            }
        });
    }

    public void generateBoard() {
        if (!HomeController.isGameStarted) return;

        hideNodes();
        List<Integer> boardsToShow = TriovisionUtils.getRandomNumbers(16);
        for (int i = 0; i < boardsToShow.size(); i++) {
            String circleToShow = CIRCLE + boardsToShow.get(i);
            showNodes(circleToShow, i);
        }
    }

    public void showNodes(String circleToShow, int colorId) {
        ObservableList<Node> circles = boardPane.getChildren();
        for (Node node : circles) {
            if (Objects.isNull(node.getId())) continue;

            if (node.getId().equals(circleToShow)) {
                node.setVisible(true);
                Circle circle = (Circle) node;
                circle.setFill(Color.valueOf(BoardColors.getColorById(colorId)));
            }
        }
    }

    public void hideNodes() {
        ObservableList<Node> circles = boardPane.getChildren();
        for (Node node : circles) {
            if (Objects.isNull(node.getId())) continue;
            node.setVisible(false);
        }
    }

    public void addRectangles() {
        for (int i = 0; i < BOARD_WIDTH; i += CELL_WIDTH) {
            for (int j = 0; j < BOARD_HEIGHT; j += CELL_HEIGHT) {
                Rectangle r = new Rectangle(i, j, CELL_WIDTH, CELL_HEIGHT);
                r.setFill(Color.valueOf(DISABLE_COLOR));
                r.setStroke(Color.BLACK);
                r.setVisible(true);

                boardPane.getChildren().add(r);
            }
        }
    }

    public void addCircles() {
        int id = 1;
        for (int i = 50; i < BOARD_WIDTH; i+= CELL_WIDTH) {
            for (int j = 38; j < BOARD_HEIGHT; j += CELL_HEIGHT, id++) {
                Circle c = new Circle();
                c.setFill(Color.valueOf(DISABLE_COLOR));
                c.setStroke(Color.BLACK);
                c.setId(CIRCLE + id);
                double radius = 35;
                Piece p = new Piece(i, j, radius, c);
                pieces.add(p);

                c.setOnMousePressed(mouseEvent -> pressed(mouseEvent, p));
                c.setOnMouseDragged(mouseEvent -> dragged(mouseEvent, p));
                c.setOnMouseReleased(mouseEvent -> released(mouseEvent, p));

                boardPane.getChildren().add(c);
                p.draw();
            }
        }
    }

    Paint color;
    double initialX, initialY;
    private void pressed(MouseEvent mouseEvent, Piece p) {
        initialX = p.getX();
        initialY = p.getY();

        color = p.getC().getFill();
        p.setColor(Color.DARKGRAY);
    }

    private void dragged(MouseEvent mouseEvent, Piece p) {
        p.setX(p.getX() + mouseEvent.getX());
        p.setY(p.getY() + mouseEvent.getY());
        p.draw();
    }

    private void released(MouseEvent mouseEvent, Piece p) {
        p.setColor((Color) color);

        double differenceX = Math.abs(p.getX() - initialX);
        double differenceY = Math.abs(p.getY() - initialY);

        if(differenceX > 125 || differenceY > 100) {
            TriovisionUtils.showAlert("You can only jump to next cell.");
            p.setX(initialX);
            p.setY(initialY);
            p.draw();
        }
    }
}