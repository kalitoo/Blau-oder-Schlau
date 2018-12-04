package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionUnit;

import java.util.List;

public interface DatabaseManagerContract {
    /** get instance of data source, database must be a singleton */
    interface Model {
        /** loads specific game from history and return it */
        Game loadGameFromHistory(int pos); // TODO choose better identifier than 'int pos'
        /** loads all games from history and returns them */
        List<Game> loadAllGamesFromHistory();
        /** loads a random question using @param seed for randomization */
        QuestionUnit loadQuestion(long seed);
        /** loads a random bundle of different questions of size @param bundleSize */
        List<QuestionUnit> loadQuestionBundle(long seed, int bundleSize);
        /** saves game in database*/
        void saveGame(Game game);
    }
}
