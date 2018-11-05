package com.example.blauoderschlau.logic;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.contracts.MainMenuContract;
import com.example.blauoderschlau.model.FakeDataProvider;
import com.example.blauoderschlau.model.Game;

import java.util.List;

public class MainMenuPresenter implements MainMenuContract.Presenter {

    private MainMenuContract.View view;
    private DatabaseManagerContract.Model model;

    public MainMenuPresenter(MainMenuContract.View view){
        this.view = view;
        model = new FakeDataProvider();
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
