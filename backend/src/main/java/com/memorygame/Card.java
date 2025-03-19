package com.memorygame;

public class Card {
    private int value;
    private String id;
    private int row;
    private int col;
    private boolean matched = false;

    public Card(int value, String id) {
        this.value = value;
        this.id = id;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}