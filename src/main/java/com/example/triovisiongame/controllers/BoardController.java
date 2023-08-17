package com.example.triovisiongame.controllers;

import com.example.triovisiongame.enums.BoardColors;
import com.example.triovisiongame.models.Piece;
import com.example.triovisiongame.utils.TriovisionUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

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
            if (Objects.isNull(node.getId())) {
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
                pieces.get(TriovisionUtils.findPieceInList(pieces, circle.getId())).setVisible(true);
            }
        }
    }

    public void hideNodes() {
        ObservableList<Node> circles = boardPane.getChildren();
        for (Node node : circles) {
            if (Objects.isNull(node.getId())) continue;
            node.setVisible(false);
        }

        for (Piece p : pieces) {
            p.setVisible(false);
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
        int row = 1;
        int column = 1;

        for (int i = START_WIDTH; i < BOARD_WIDTH; i += CELL_WIDTH, column++) {
            for (int j = START_HEIGHT; j < BOARD_HEIGHT; j += CELL_HEIGHT, id++, row++) {
                Circle c = new Circle();
                c.setFill(Color.valueOf(DISABLE_COLOR));
                c.setStroke(Color.BLACK);
                c.setId(CIRCLE + id);
                double radius = 35;
                Piece p = new Piece(i, j, radius, c);
                p.setInitialX(i);
                p.setInitialY(j);
                p.setBoardRow(row);
                p.setBoardColumn(column);
                pieces.add(p);

                c.setOnMousePressed(mouseEvent -> pressed(mouseEvent, p));
                c.setOnMouseDragged(mouseEvent -> dragged(mouseEvent, p));
                c.setOnMouseReleased(mouseEvent -> released(mouseEvent, p));

                boardPane.getChildren().add(c);
                p.draw();

                row = row == 4 ? 0 : row;
            }

            column = column == 4 ? 0 : column;
        }
    }

    Paint color;

    private void pressed(MouseEvent mouseEvent, Piece p) {
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
        if (Objects.isNull(HomeController.selectedCard)) {
            TriovisionUtils.showAlert("Please select a card first.");
            p.setX(p.getInitialX());
            p.setY(p.getInitialY());
            p.draw();
            return;
        }

        if (!isJumpedMoreThan1Cell(p)) {
            updateRowColumnIndex(p);
            matchPattern();
            p.setX(p.getInitialX());
            p.setY(p.getInitialY());
            generateBoard();
        }
    }

    private void updateRowColumnIndex(Piece p) {
        int newRow, newColumn;
        newColumn = (int) (p.getX() + START_WIDTH + MARGIN) / CELL_WIDTH;
        newRow = (int) (p.getY() + START_HEIGHT + MARGIN) / CELL_HEIGHT;
        p.setBoardRow(newRow);
        p.setBoardColumn(newColumn);
    }

    private boolean isJumpedMoreThan1Cell(Piece p) {
        double differenceX = Math.abs(p.getX() - p.getInitialX());
        double differenceY = Math.abs(p.getY() - p.getInitialY());

        if (differenceX > 125 || differenceY > 100) {
            TriovisionUtils.showAlert("You can only jump to next cell.");
            p.setX(p.getInitialX());
            p.setY(p.getInitialY());
            p.draw();
            return true;
        }

        return false;
    }

    private void matchPattern() {
        boolean isMatched = true;
        for (int boardRow = 1; boardRow <= 2; boardRow++) {
            for (int boardCol = 1; boardCol <= 3; boardCol++) {
                isMatched = true;
                for (int cardRow = 1; cardRow <= 3; cardRow++) {
                    for (int cardCol = 1; cardCol <= 2; cardCol++) {
                        Paint cardPieceColor = HomeController.selectedCard.getColorByIndex(cardRow, cardCol);
                        Paint boardPieceColor;
                        Piece boardPiece = TriovisionUtils.findPieceByRowColumn(pieces, boardRow + cardRow-1, boardCol + cardCol - 1);
                        boardPieceColor = Objects.isNull(boardPiece) ? Color.TRANSPARENT : boardPiece.getC().getFill();

                        if (cardPieceColor.equals(Color.TRANSPARENT)) continue;
                        if (!cardPieceColor.equals(boardPieceColor)) isMatched = false;
                    }
                }
                if(isMatched) break;
            }
            if(isMatched) break;
        }

        updateScore(isMatched);

    }

    private void updateScore (boolean isMatched) {
        if(isMatched) {
            if(HomeController.isMultiPlayerSelected) {
                if (HomeController.isPlayer1Playing) HomeController.p1Score++;
                else HomeController.p2Score++;
                changeTurns();
            } else {
                HomeController.p1Score++;
                TriovisionUtils.showAlert("Your Score is: " + HomeController.p1Score);
            }
        }
    }

    public void changeTurns() {
        HomeController.isPlayer1Playing = !HomeController.isPlayer1Playing;
        HomeController.isPlayer2Playing = !HomeController.isPlayer2Playing;

        if(HomeController.isPlayer1Playing) TriovisionUtils.showAlert("Player 1 playing now! \nPlayer 2 Score: " + HomeController.p2Score);
        else TriovisionUtils.showAlert("Player 2 playing now! \nPlayer 1 Score: " + HomeController.p1Score);
    }

}