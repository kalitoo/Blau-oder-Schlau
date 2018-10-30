package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.Question;

public interface DatabaseManagerContract {
    interface Model{
        Game loadGameFromHistory(int pos); // TODO choose better identifier than 'int pos'
        Question getQuestion(long seed);
        Question[] getQuestionBundle(long seed);
    }
}
