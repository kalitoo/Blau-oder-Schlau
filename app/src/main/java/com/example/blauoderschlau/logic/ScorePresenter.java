package com.example.blauoderschlau.logic;


import android.content.Context;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.contracts.ScoreContract;
import com.example.blauoderschlau.database.BosDatabaseHelper;
import com.example.blauoderschlau.database.MyDB;
import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionResult;

import java.util.List;

public class ScorePresenter implements ScoreContract.Presenter {

    private ScoreContract.View view;
    private DatabaseManagerContract.Model model;

    private static final int AVG_MS_CONSIDERED_SOBER = 2500;
    private static final int MAX_MS = 10000;
    private static final double MAX_POSSIBLE_PER_MILL = 3.;

    public ScorePresenter(ScoreContract.View view, Context context){
        this.view = view;


        model = MyDB.getInstance(context);
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
        for (QuestionResult qr : questionResultList) {
            avTTA += (qr.correctAnswerSelected ? qr.msToAnswerSelected : MAX_MS);
            cnt++;
        }
        avTTA /= (cnt == 0 ? 1 : cnt);

        if(avTTA < AVG_MS_CONSIDERED_SOBER){
            avTTA = AVG_MS_CONSIDERED_SOBER;
        }


        Game returnGame = new Game();
        returnGame.setDate();
        returnGame.setPerMill(calculatePerMillValue(avTTA));
        return returnGame;

    }

    // TODO write test for this
    private double calculatePerMillValue(long avTTA) {
        /*
         * y: per mill value, x: avTTA
         * y = mx + b
         * y(AVG_MS_CONSIDERED_SOBER) = 0
         * y(MAX_MS) = MAX_POSSIBLE_PER_MILL
         */
        final double m = MAX_POSSIBLE_PER_MILL / (MAX_MS - AVG_MS_CONSIDERED_SOBER);
        final double b = 0. - m * AVG_MS_CONSIDERED_SOBER;
        return m * avTTA + b;
    }
}
