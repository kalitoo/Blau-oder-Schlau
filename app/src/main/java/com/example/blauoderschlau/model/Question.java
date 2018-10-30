package com.example.blauoderschlau.model;

public class Question {
    private String q;
    private String[] answerOptions;
    private String correctAnswer; // TODO think about better solutions

    public Question(){
        answerOptions = new String[4];
    }

    public String getQ(){
        return q;
    }

    public void setQ(final String q) {
        this.q = q;
    }

    public String[] getAnswerOptions(){
        return answerOptions;
    }

    public void setAnswerOptions(String[] answerOptions) {
        this.answerOptions = answerOptions;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
