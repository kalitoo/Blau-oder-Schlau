package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionUnit;

import java.util.List;

public interface DatabaseManagerContract {
    interface Model{
        Game loadGameFromHistory(int pos); // TODO choose better identifier than 'int pos'
        List<Game> loadAllGamesFromHistory();
        QuestionUnit getQuestion(long seed);
        List<QuestionUnit> getQuestionBundle(long seed, int bundleSize);
    }
}
