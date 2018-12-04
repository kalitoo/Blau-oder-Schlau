package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionResult;

import java.util.List;

public interface ScoreContract {
    interface View {
        /** shows quiz results */
        void showResults(Game game);
    }

    interface Presenter {
        /** called when returning to home screen*/
        void returnToHome();

        /** builds game from question result list and persists it */
        Game buildAndSaveGame(List<QuestionResult> questionResultList);
    }
}
