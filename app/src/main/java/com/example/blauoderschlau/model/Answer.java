package com.example.blauoderschlau.model;

public class Answer {

    private String answer;
    private boolean correct;

    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getText() {
        return answer;
    }

}
