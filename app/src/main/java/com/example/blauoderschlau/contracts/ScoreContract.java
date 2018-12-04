package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionResult;

import java.util.List;

public interface ScoreContract {
    interface View {
        /** shows quiz results */
        void showResults(Game game);

        /** switches to main menu activity */
        void goToMainMenu();
    }

    interface Presenter {
        /** builds game from question result list and persists it */
        void buildAndSaveGame(List<QuestionResult> questionResultList);

        /** called when returning to home screen*/
        void returnToHome();
    }
}
