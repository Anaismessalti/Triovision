package com.example.triovisiongame.utils;

import com.example.triovisiongame.models.Piece;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TriovisionUtils {

    /**
     * The method returns list of 8 unique random numbers in range [1-16]
     * The board pattern will be generated based on this
     */
    public static List getRandomNumbers(int maxSize) {
        List<Integer> uniqueRandNumList = new ArrayList<>();
        for (int i = 1; i <= maxSize; i++) {
            uniqueRandNumList.add(i);
        }
        Collections.shuffle(uniqueRandNumList);
        uniqueRandNumList = uniqueRandNumList.subList(1, 9);

        return uniqueRandNumList;
    }

    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Trio-vision Game Alert");
        alert.setContentText(message);
        alert.show();
    }


    public static int getRandNumInGivenRange(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static Integer findPieceInList(List<Piece> pieces, String circleId) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getC().getId().equals(circleId)) {
                return i;
            }
        }
        return null;
    }

    public static Piece findPieceByRowColumn(List<Piece> pieces, int row, int column) {
        for (Piece piece : pieces) {
            if(piece.isVisible() && piece.getBoardRow() == row && piece.getBoardColumn()==column) {
                return piece;
            }
        }

        return null;
    }

    public static double[] getNewPiecePosition(List<Piece> pieces, Piece p) {
        double[] newPositionXY = new double[2];
        for (Piece piece: pieces) {
            double draggedX = p.getX() + 25;
            double draggedY = p.getY() + 25;

            if(piece.getC().getId().equals(p.getC().getId())) continue;

            if(piece.getX() <= draggedX && piece.getY() <= draggedY) {
                newPositionXY[0] = piece.getInitialX();
                newPositionXY[1] = piece.getInitialY();
            }
        }

        return newPositionXY;
    }
}
