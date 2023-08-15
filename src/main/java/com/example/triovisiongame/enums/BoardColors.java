package com.example.triovisiongame.enums;

import com.example.triovisiongame.common.GameConstants;

public enum BoardColors {
    RED (0, GameConstants.RED),
    GREEN(1, GameConstants.GREEN),
    BLUE(2, GameConstants.BLUE),
    YELLOW(3, GameConstants.YELLOW),

    RED1 (4, GameConstants.RED),
    GREEN1(5, GameConstants.GREEN),
    BLUE1(6,  GameConstants.BLUE),
    YELLOW1(7, GameConstants.YELLOW);

    private int id;
    private String code;

    BoardColors(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public static String getColorById(int id) {
        for (BoardColors color: BoardColors.values()) {
            if(id == color.getId()) {
                return color.getCode();
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
