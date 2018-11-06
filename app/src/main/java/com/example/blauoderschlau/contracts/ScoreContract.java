package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.QuestionResult;

public interface ScoreContract {
    interface View {
        /** shows quiz results */
        void showResults(QuestionResult questionResult);
    }

    interface Presenter {
        /** called when returning to home screen*/
        void returnToHome();
    }
}
