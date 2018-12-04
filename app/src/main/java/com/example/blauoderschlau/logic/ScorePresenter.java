package com.example.blauoderschlau.logic;

import android.provider.ContactsContract;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.contracts.QuizContract;
import com.example.blauoderschlau.contracts.ScoreContract;
import com.example.blauoderschlau.model.FakeDataProvider;
import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionResult;

import java.util.List;

public class ScorePresenter implements ScoreContract.Presenter {

    private ScoreContract.View view;
    private DatabaseManagerContract.Model model;

    public ScorePresenter(ScoreContract.View view){
        this.view = view;
        this.model = DatabaseManagerContract.dataSource;
    }

    @Override
    public void buildAndSaveGame(List<QuestionResult> questionResultList){
        Game gameJustFinished = makeGame(questionResultList);
        model.saveGame(gameJustFinished);
        view.showResults(gameJustFinished);
    }

    @Override
    public void returnToHome() {
        view.goToMainMenu();
    }

    private Game makeGame(List<QuestionResult> questionResultList) {
        long avTTA = 0;
        int cnt = 0;
        int wrongAnswers = 0;
        for (QuestionResult qr : questionResultList) {
            avTTA += qr.msToAnswerSelected;
            wrongAnswers += (qr.correctAnswerSelected ? 0 : 1);
            cnt++;
        }
        avTTA /= (cnt == 0 ? 1 : cnt);

        return new Game(
                calculatePerMillValue(avTTA, wrongAnswers, cnt - wrongAnswers));

    }

    private double calculatePerMillValue(long avTTA, int wrongAnswers, int correctAnswers) {
        return 0;
    }
}
