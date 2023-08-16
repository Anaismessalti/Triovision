package com.example.triovisiongame.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {
    private double initialX;
    private double initialY;
    private boolean isVisible;
    private double x;
    private double y;
    private double radius;

    private  int boardRow;
    private  int boardColumn;
    private Circle c;

    public Piece() {
    }

    public Piece(double x, double y, double radius, Circle c) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.c = c;
    }

    public void setColor (Color color) {
        c.setFill(color);
    }

    public void draw () {
        c.setRadius(radius);
        c.setTranslateX(x);
        c.setTranslateY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle getC() {
        return c;
    }

    public void setC(Circle c) {
        this.c = c;
    }

    public double getInitialX() {
        return initialX;
    }

    public void setInitialX(double initialX) {
        this.initialX = initialX;
    }

    public double getInitialY() {
        return initialY;
    }

    public void setInitialY(double initialY) {
        this.initialY = initialY;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getBoardRow() {
        return boardRow;
    }

    public void setBoardRow(int boardRow) {
        this.boardRow = boardRow;
    }

    public int getBoardColumn() {
        return boardColumn;
    }

    public void setBoardColumn(int boardColumn) {
        this.boardColumn = boardColumn;
    }
}
