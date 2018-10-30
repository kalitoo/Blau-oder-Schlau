package com.example.blauoderschlau.model;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataProvider implements DatabaseManagerContract.Model {

    private static List<Question> questions = new ArrayList<>();
    private static List<Game> gameHistory = new ArrayList<>();

    static {
        Question q0 = new Question();
        q0.setQ("Was ist 1+1?");
        q0.setAnswerOptions(new String[]{"0","1","2","3"});
        q0.setCorrectAnswer("2");
        Question q1 = new Question();
        q1.setQ("Wie heißt die Mutter von Niki Lauda?");
        q1.setAnswerOptions(new String[]{"Sebastian Vettel","Mama Lauda","Toto Wolff","Steve Jobs"});
        q1.setCorrectAnswer("Mama Lauda");
        questions.add(q0);
        questions.add(q1);

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
    public Question getQuestion(long seed) {
        int indexOfRandQuestion = new Random().nextInt(questions.size());
        return questions.get(indexOfRandQuestion);
    }

    @Override
    public List<Question> getQuestionBundle(long seed, int bundleSize) {
        List<Question> questionBundle = new ArrayList<>();
        for(int i = 0; i < bundleSize; i++) {
            if (i >= questions.size()){
                break;
            }
            questionBundle.add(questions.get(i));
        }
        return questionBundle;
    }
}
