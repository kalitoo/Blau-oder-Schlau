package com.example.blauoderschlau.model;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataProvider implements DatabaseManagerContract.Model {

    private static List<QuestionUnit> questionUnits = new ArrayList<>();
    private static List<Game> gameHistory = new ArrayList<>();

    static {
        String q0 = "Was ist 1+1?";
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
        String q2 = "Klicke auf Deutschland";
        Answer[] ao2 = new Answer[]{
                new Answer("Deutschland", true),
                new Answer("Klick", false),
                new Answer("Ich bin nicht betrunken", false),
                new Answer("Bayern", false)};
        QuestionUnit questionUnit2 = new QuestionUnit(q2, ao2);
        String q3 = "Klicke auf Deutschland";
        Answer[] ao3 = new Answer[]{
                new Answer("Duetschland", false),
                new Answer("Deustchland", false),
                new Answer("Deutschalnd", false),
                new Answer("Deutschland", true)};
        QuestionUnit questionUnit3 = new QuestionUnit(q3, ao3);
        String q4 = "Wähle die Schaltfläche mit dem Text \"oben rechts\"";
        Answer[] ao4 = new Answer[]{
                new Answer("oben rechts", true),
                new Answer("unten links", false),
                new Answer("oben links", false),
                new Answer("unten rechts", false)};
        QuestionUnit questionUnit4 = new QuestionUnit(q4, ao4);
        String q5 = "Wer ist der allergeilste?";
        Answer[] ao5 = new Answer[]{
                new Answer("Hower", true),
                new Answer("Hoffmann", false),
                new Answer("Herzog", false),
                new Answer("Jost", false)};
        QuestionUnit questionUnit5 = new QuestionUnit(q5, ao5);
        questionUnits.add(questionUnit0);
        questionUnits.add(questionUnit1);
        questionUnits.add(questionUnit2);
        questionUnits.add(questionUnit3);
        questionUnits.add(questionUnit4);
        questionUnits.add(questionUnit5);

        for (int i = 0; i < 50; i++) {
            gameHistory.add(new Game(i, "11.12.13"));
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
    public QuestionUnit loadQuestion(long seed) {
        int indexOfRandQuestion = new Random().nextInt(questionUnits.size());
        return questionUnits.get(indexOfRandQuestion);
    }

    @Override
    public List<QuestionUnit> loadQuestionBundle(long seed, int bundleSize) {
        List<QuestionUnit> questionUnitBundle = new ArrayList<>();
        for (int i = 0; i < bundleSize; i++) {
            if (i >= questionUnits.size()) {
                break;
            }
            questionUnitBundle.add(questionUnits.get(i));
        }
        return questionUnitBundle;
    }
}
