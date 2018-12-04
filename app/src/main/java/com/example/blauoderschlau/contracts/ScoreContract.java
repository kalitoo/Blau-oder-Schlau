package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;

public interface ScoreContract {
    interface View {
        /** shows quiz results */
        void showResults(Game game);
    }

    interface Presenter {
        /** called when returning to home screen*/
        void returnToHome();
    }
}
