package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Answer;
import com.example.blauoderschlau.model.QuestionUnit;

public interface QuizContract {
    interface View {
        void markAnswerAsRight(EAnswerOption pos);

        void markAnswerAsWrong(EAnswerOption pos);

        void showEntireQuestion(QuestionUnit q);

        void showQuestionString(String q);

        void showAnswerOptions(Answer[] answers);

        void showAnswer(String a, EAnswerOption pos);

        void lastQuestionAnswered();

        void resetAllMarkings();
    }

    interface Presenter {
        void answerClicked(EAnswerOption pos);

        void aborted();
    }

    enum EAnswerOption {
        A, B, C, D
    }
}
