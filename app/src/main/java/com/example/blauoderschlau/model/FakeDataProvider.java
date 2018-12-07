package com.example.blauoderschlau.model;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataProvider implements DatabaseManagerContract.Model {

    private static List<QuestionUnit> questionUnits = new ArrayList<>();
    private static List<Game> gameHistory = new ArrayList<>();

    private static FakeDataProvider instance = new FakeDataProvider();

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
        String q5 = "Wie viele Wörter beinhaltet diese Frage?";
        Answer[] ao5 = new Answer[]{
                new Answer("4", false),
                new Answer("5", false),
                new Answer("6", true),
                new Answer("7", false)};
        QuestionUnit questionUnit5 = new QuestionUnit(q5, ao5);
        String q6 = "Wie viele Plus-Zeichen siehst du?\n++++++";
        Answer[] ao6 = new Answer[]{
                new Answer("6", true),
                new Answer("7", false),
                new Answer("8", false),
                new Answer("9", false)};
        QuestionUnit questionUnit6 = new QuestionUnit(q6, ao6);
        String q7 = "Wo zeigt die Mehrheit der Pfeile nach rechts?";
        Answer[] ao7 = new Answer[]{
                new Answer(">><", true),
                new Answer("<><", false),
                new Answer("><<", false),
                new Answer("AfD", false)};
        QuestionUnit questionUnit7 = new QuestionUnit(q7, ao7);
        String q8 = "Wie lustig fandest du die letzte Frage?";
        Answer[] ao8 = new Answer[]{
                new Answer("Allerhöchstens Schenkelklopfer", false),
                new Answer("Ziemlich Arm", false),
                new Answer("Sehr Bein", false),
                new Answer("Höchst amüsant", true)};
        QuestionUnit questionUnit8 = new QuestionUnit(q8, ao8);
        String q9 = "Wie viel Sterne würdest du dieser App geben?";
        Answer[] ao9 = new Answer[]{
                new Answer("Einen", false),
                new Answer("Zwei", false),
                new Answer("Drei", false),
                new Answer("Fünf", true)};
        QuestionUnit questionUnit9 = new QuestionUnit(q9, ao9);
        String q10 = "Was ist aus medizinischer Sicht kein Affe?";
        Answer[] ao10 = new Answer[]{
                new Answer("Orang-Utan", false),
                new Answer("Schimpanse", false),
                new Answer("Kapuzineräffchen", false),
                new Answer("Oliver Kahn", true)};
        QuestionUnit questionUnit10 = new QuestionUnit(q10, ao10);
        String q11 = "Wer sollte sich noch weitere Fragen ausdenken?";
        Answer[] ao11 = new Answer[]{
                new Answer("Jan und Jonas", false),
                new Answer("Jonathan und Jonas", false),
                new Answer("nur Jonas", false),
                new Answer("Jan und Jonathan", true)};
        QuestionUnit questionUnit11 = new QuestionUnit(q11, ao11);
        questionUnits.add(questionUnit0);
        questionUnits.add(questionUnit1);
        questionUnits.add(questionUnit2);
        questionUnits.add(questionUnit3);
        questionUnits.add(questionUnit4);
        questionUnits.add(questionUnit5);
        questionUnits.add(questionUnit6);
        questionUnits.add(questionUnit7);
        questionUnits.add(questionUnit8);
        questionUnits.add(questionUnit9);
        questionUnits.add(questionUnit10);
        questionUnits.add(questionUnit11);


        for (int i = 0; i < 50; i++) {
            gameHistory.add(new Game(i));
        }
    }

    private FakeDataProvider(){

    }


    public static DatabaseManagerContract.Model getInstance() {
        if(instance == null){
            instance = new FakeDataProvider();
        }
        return instance;
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

    @Override
    public void saveGame(Game game) {

    }
}
