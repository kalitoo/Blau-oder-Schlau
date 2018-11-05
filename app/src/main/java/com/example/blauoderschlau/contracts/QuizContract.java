package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Question;

public interface QuizContract {
    interface View {
        void markAnswerAsRight(int pos); // implement this
        void markAnswerAsWrong(int pos);
        void showQuestion(Question q);
    }
    interface Presenter {
        void answerClicked(int pos);
        void aborted();
    }
}
