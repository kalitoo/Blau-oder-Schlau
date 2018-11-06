package com.example.blauoderschlau.model;

import android.util.Log;

public class QuestionUnit {

    private String q;
    // boolean indices whether answer is correct
    private Answer[] answers;
    private Answer correctAnswer;

    public QuestionUnit(final String q, final Answer[] answers){
        this.q = q;
        this.answers = answers;
        assignCorrectAnswer();
        if(!isValidQuestion()){
            Log.w("invalidquestion", "You just created an invalid question!");
        }
    }

    public String getQ(){
        return q;
    }

    public void setQ(final String q) {
        this.q = q;
    }

    public Answer[] getAnswers(){
        return answers;
    }

    public Answer getCorrectAnswer(){
        if(correctAnswer == null){
            isValidQuestion();
            assignCorrectAnswer();
        }
        return correctAnswer;
    }

    private void assignCorrectAnswer(){
        for(Answer answer : answers) {
            if(answer.isCorrect()){
                correctAnswer = answer;
            }
        }
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public boolean isValidQuestion() {
        int i = 0;
        int e = 0;
        for(Answer answer : answers) {
            if(answer.isCorrect()){
                i++;
            }
            if(answer.getText().isEmpty()){
                e++;
            }
        }

        if(!q.isEmpty() && i == 1 && e == 0){
            return true;
        }
        else{
            Log.w("invalidquestion", "Invalid question: There are " + i + " correct answer" +
                    "options" + "and " + e + " empty answer options");
            return false;
        }
    }

}
