package com.example.blauoderschlau.model;

import java.util.Date;

public class Game {

    // time stamp when game was finished
    private Date date;
    // the higher, the better (the less drunk)
    private int score;

    public Game(int score) {
        this.score = score;
        this.date = new Date();
    }

    public int getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }
}
