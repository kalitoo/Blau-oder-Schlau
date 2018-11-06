package com.example.blauoderschlau.model;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataProvider implements DatabaseManagerContract.Model {

    private static List<QuestionUnit> questionUnits = new ArrayList<>();
    private static List<Game> gameHistory = new ArrayList<>();

    static {
        String q0 ="Was ist 1+1?";
        Answer[] ao0 = new Answer[]{
                new Answer("0", false),
                new Answer("1", false),
                new Answer("2", true),
                new Answer("3", false)};
        QuestionUnit questionUnit0 = new QuestionUnit(q0, ao0);
        String q1 = "Wie heißt die Mutter von Niki Lauda?";
        Answer[] ao1 = new Answer[]{
                new Answer("Sebastian Vettel", false),
                new Answer("Mama Lauda", true),
                new Answer("Toto Wolff", false),
                new Answer("Steve Jobs", false)};
        QuestionUnit questionUnit1 = new QuestionUnit(q1, ao1);
        questionUnits.add(questionUnit0);
        questionUnits.add(questionUnit1);

        for(int i = 0; i < 50; i++){
            gameHistory.add(new Game(i,"11.12.13"));
        }
    }

    @Override
    public Game loadGameFromHistory(int pos) {
        return null;
    }

    @Override
    public List<Game> loadAllGamesFromHistory() {
        return gameHistory;
    }

    @Override
    public QuestionUnit getQuestion(long seed) {
        int indexOfRandQuestion = new Random().nextInt(questionUnits.size());
        return questionUnits.get(indexOfRandQuestion);
    }

    @Override
    public List<QuestionUnit> getQuestionBundle(long seed, int bundleSize) {
        List<QuestionUnit> questionUnitBundle = new ArrayList<>();
        for(int i = 0; i < bundleSize; i++) {
            if (i >= questionUnits.size()){
                break;
            }
            questionUnitBundle.add(questionUnits.get(i));
        }
        return questionUnitBundle;
    }
}
