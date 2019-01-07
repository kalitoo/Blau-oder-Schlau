package com.example.blauoderschlau.logic;

import android.content.Context;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.contracts.MainMenuContract;
import com.example.blauoderschlau.database.BosDatabaseHelper;
import com.example.blauoderschlau.database.MyDB;
import com.example.blauoderschlau.model.Game;

import java.util.List;

public class MainMenuPresenter implements MainMenuContract.Presenter {

    private MainMenuContract.View view;
    private DatabaseManagerContract.Model model;

    public MainMenuPresenter(MainMenuContract.View view, Context context) {
        this.view = view;
        //model = DatabaseManagerContract.dataSource;
        //model = BosDatabaseHelper.getInstance(context);
        model = MyDB.getInstance(context);
    }

    @Override
    public void init() {
        // load history
        List<Game> games = model.loadAllGamesFromHistory();
        view.showGameHistory(games);
    }

    @Override
    public void startButtonClicked() {
        view.startQuiz();
    }

    @Override
    public void gameFromHistoryClicked(int i) {

    }
}
