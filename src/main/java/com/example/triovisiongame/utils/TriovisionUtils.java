package com.example.triovisiongame.utils;

import com.example.triovisiongame.controllers.HomeController;
import javafx.scene.control.Alert;

import java.util.*;

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
}
