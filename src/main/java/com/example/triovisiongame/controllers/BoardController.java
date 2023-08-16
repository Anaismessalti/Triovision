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
            matchPattern(p);
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


    private void placePieceInCorrectPosition(Piece p) {
        double[] newPositionXY = TriovisionUtils.getNewPiecePosition(pieces, p);
        p.setX(newPositionXY[0]);
        p.setY(newPositionXY[1]);
        p.draw();
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

    private void matchPattern(Piece p) {
        boolean isMatched = true;
        for (int boardRow = 1; boardRow <= 3; boardRow++) {
            for (int boardCol = 1; boardCol <= 2; boardCol++) {
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


        if (isMatched) {
            HomeController.p1Score++;
            System.out.println(HomeController.p1Score);
        }
    }


    private void matchPattern3(Piece p) {
        int startRow = p.getBoardRow(), startColumn = p.getBoardColumn();
        int runningRow = startRow + 1;
        int runningColumn = startColumn;

        if (!colorMatched(p)) return;
        Set<Piece> matchedPieces = runMatching(runningRow, runningColumn, 0);

        if (HomeController.selectedCard.isPatternMatched()) {
            HomeController.p1Score++;
        }
    }

    private Set<Piece> runMatching(int runningRow, int runningColumn, int counter) {
        Set<Piece> matchedPieces = new HashSet<>();
        Piece adjacentPiece = TriovisionUtils.findPieceByRowColumn(pieces, runningRow++, runningColumn);
        if (counter == 16) return matchedPieces;
        counter++;

        if (Objects.nonNull(adjacentPiece) && colorMatched(adjacentPiece)) {
            matchedPieces.add(adjacentPiece);
            runningRow++;
            runningColumn--;
            return runMatching(runningRow, runningColumn, counter);
        } else {
            return runMatching(runningRow--, runningColumn, counter);
        }
    }


    private boolean colorMatched(Piece p) {
        Paint[] paintArr = new Paint[3];
        paintArr[0] = HomeController.selectedCard.circle1.getFill();
        paintArr[1] = HomeController.selectedCard.circle2.getFill();
        paintArr[2] = HomeController.selectedCard.circle3.getFill();

        Paint colorToMatch = p.getC().getFill();
        return paintArr[0].equals(colorToMatch) || paintArr[1].equals(colorToMatch) || paintArr[2].equals(colorToMatch);
    }

    private void matchPattern2(Piece p) {
        boolean isMatched = false;

        Paint[] paintArr = new Paint[3];
        paintArr[0] = HomeController.selectedCard.circle1.getFill();
        paintArr[1] = HomeController.selectedCard.circle2.getFill();
        paintArr[2] = HomeController.selectedCard.circle3.getFill();

        int circlePosition = getPositionOfMovedPiece(paintArr, p, HomeController.selectedCard) + 1;

        int row = p.getBoardRow();
        int column = p.getBoardColumn();
        switch (circlePosition) {
            case 1:
                Piece c1SecondPiece = TriovisionUtils.findPieceByRowColumn(pieces, row + 1, column);
                Piece c1ThirdPiece = TriovisionUtils.findPieceByRowColumn(pieces, row + 2, column - 1);
                isMatched = paintArr[1].equals(c1SecondPiece.getC().getFill()) && paintArr[2].equals(c1ThirdPiece.getC().getFill());
                break;
            case 2:
                Piece c2FirstPiece = TriovisionUtils.findPieceByRowColumn(pieces, row - 1, column);
                Piece c2ThirdPiece = TriovisionUtils.findPieceByRowColumn(pieces, row + 1, column - 1);
                isMatched = paintArr[0].equals(c2FirstPiece.getC().getFill()) && paintArr[2].equals(c2ThirdPiece.getC().getFill());
                break;
            case 3:
                Piece c3FirstPiece = TriovisionUtils.findPieceByRowColumn(pieces, row - 1, column + 1);
                Piece c3SecondPiece = TriovisionUtils.findPieceByRowColumn(pieces, row - 2, column + 1);
                isMatched = paintArr[0].equals(c3FirstPiece.getC().getFill()) && paintArr[1].equals(c3SecondPiece.getC().getFill());
                break;
            default:
        }

        if (isMatched) {
            HomeController.p1Score++;
        }
    }


    public int getPositionOfMovedPiece(Paint[] paintArr, Piece piece, CardController selectedCard) {
        boolean isTwoCirclesSame = false;
        if (paintArr[0].equals(paintArr[1]) || paintArr[0].equals(paintArr[2]) || paintArr[1].equals(paintArr[2])) {
            isTwoCirclesSame = true;
        }

        for (int i = 0; i < paintArr.length; i++) {
            if (paintArr[i].equals(piece.getC().getFill())) {
                if (!isTwoCirclesSame) return i;


            }
        }
        return -1;
    }


    public boolean isImmediateAdjacent(Piece p, Circle circle) {
        return (p.getX() + 25 <= circle.getTranslateX() || p.getY() + 25 <= circle.getTranslateY());
    }

    //   private void matchPattern1(Piece p) {
//        Set<Node> patternList = new HashSet<>();
//        boolean isMatched = false;
//        List<Node> visibleCircles = boardPane.getChildren()
//                .stream()
//                .filter(node -> Objects.nonNull(node.getId()) && node.isVisible())
//                .collect(Collectors.toList());
//
//        for (Node node : visibleCircles) {
//            Circle circle = (Circle) node;
//            if (matchColor(circle.getFill()) && isImmediateAdjacent(p, circle)) {
//                patternList.add(circle);
//            }
//        }
//
//        System.out.println(patternList);
//    }

//    private void matchPattern2(Piece p) {
//        boolean isMatched = false;
//
//        Paint[] paintArr = new Paint[3];
//        paintArr[0] = HomeController.selectedCard.circle1.getFill();
//        paintArr[1] = HomeController.selectedCard.circle2.getFill();
//        paintArr[2] = HomeController.selectedCard.circle3.getFill();
//
//        int circlePosition = getPositionOfMovedPiece(paintArr, p.getC().getFill()) + 1;
//
//        int row = p.getBoardRow();
//        int column = p.getBoardColumn();
//        switch (circlePosition) {
//            case 1:
//                Piece c1SecondPiece = TriovisionUtils.findPieceByRowColumn(pieces, row + 1, column);
//                Piece c1ThirdPiece = TriovisionUtils.findPieceByRowColumn(pieces, row + 2, column - 1);
//                isMatched = paintArr[1].equals(c1SecondPiece.getC().getFill()) && paintArr[2].equals(c1ThirdPiece.getC().getFill());
//                break;
//            case 2:
//                Piece c2FirstPiece = TriovisionUtils.findPieceByRowColumn(pieces, row - 1, column);
//                Piece c2ThirdPiece = TriovisionUtils.findPieceByRowColumn(pieces, row + 1, column - 1);
//                isMatched = paintArr[0].equals(c2FirstPiece.getC().getFill()) && paintArr[2].equals(c2ThirdPiece.getC().getFill());
//                break;
//            case 3:
//                Piece c3FirstPiece = TriovisionUtils.findPieceByRowColumn(pieces, row - 1, column + 1);
//                Piece c3SecondPiece = TriovisionUtils.findPieceByRowColumn(pieces, row - 2, column + 1);
//                isMatched = paintArr[0].equals(c3FirstPiece.getC().getFill()) && paintArr[1].equals(c3SecondPiece.getC().getFill());
//                break;
//            default:
//        }
//
//        if (isMatched) {
//            HomeController.p1Score++;
//        }
//    }

}