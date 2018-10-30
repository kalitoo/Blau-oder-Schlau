package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.Question;

import java.util.List;

public interface DatabaseManagerContract {
    interface Model{
        Game loadGameFromHistory(int pos); // TODO choose better identifier than 'int pos'
        List<Game> loadAllGamesFromHistory();
        Question getQuestion(long seed);
        Question[] getQuestionBundle(long seed);
    }
}
