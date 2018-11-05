package com.example.blauoderschlau.contracts;

public interface ScoreContract {
    interface View {
        void showResults(int score);
    }

    interface Presenter {
        void returnToHome();
    }
}
