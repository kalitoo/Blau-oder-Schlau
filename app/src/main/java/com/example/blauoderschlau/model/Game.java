package com.example.blauoderschlau.model;

public class Game {

    int score;
    String date;

    public Game(int score, String date) {
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
}
