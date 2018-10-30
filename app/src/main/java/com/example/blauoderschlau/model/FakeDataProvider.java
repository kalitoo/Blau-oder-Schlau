package com.example.blauoderschlau.model;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;

import java.util.ArrayList;
import java.util.List;

public class FakeDataProvider implements DatabaseManagerContract.Model {

    @Override
    public Game loadGameFromHistory(int pos) {
        return null;
    }

    @Override
    public List<Game> loadAllGamesFromHistory() {
        List<Game> games = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            games.add(new Game(i,"11.12.13"));
        }
        return games;
    }

    @Override
    public Question getQuestion(long seed) {
        return null;
    }

    @Override
    public Question[] getQuestionBundle(long seed) {
        return new Question[0];
    }
}
