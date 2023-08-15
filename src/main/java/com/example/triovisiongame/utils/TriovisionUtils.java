package com.example.triovisiongame.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TriovisionUtils {

    /**
     * The method returns list of 8 unique random numbers in range [1-16]
     * The board pattern will be generated based on this
     */
    public static List getRandomNumbers() {
        Integer[] array4x4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        List<Integer> uniqueRandNumList = Arrays.asList(array4x4);
        Collections.shuffle(uniqueRandNumList);

        uniqueRandNumList = uniqueRandNumList.subList(1, 9);
        return uniqueRandNumList;
    }


    public static int getRandNumInGivenRange(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
