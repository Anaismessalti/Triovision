package com.example.triovisiongame.utils;

import com.example.triovisiongame.models.Piece;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

}
