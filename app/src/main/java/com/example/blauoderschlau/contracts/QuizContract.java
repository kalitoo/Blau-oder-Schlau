package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.QuestionResult;
import com.example.blauoderschlau.model.QuestionUnit;

import java.util.Collection;

public interface QuizContract {
    interface View {
        /** show that the answer at @param pos is right */
        void markAnswerAsRight(EAnswerOption pos);
        /** show that the answer as @param pos is wrong */
        void markAnswerAsWrong(EAnswerOption pos);
        /** show question with its answer options (@param q contains them) */
        void showEntireQuestion(QuestionUnit q);
        /** refresh question text only */
        void showQuestionString(String q);
        /** show answer with text @param a at position @param pos */
        void showAnswer(String a, EAnswerOption pos);
        /** called when last question has been answered */
        void lastQuestionAnswered(Collection<QuestionResult> questionResultCollection);
        /** reset button look to default*/
        void resetAllMarkings();
    }

    interface Presenter {
        /** called when user has clicked the answer at position @param pos */
        void answerClicked(EAnswerOption pos);
        /** called when the quiz has been cancelled in any way */
        void aborted();
    }

    /**
     * use this to label answer options
     */
    enum EAnswerOption {
        A, B, C, D
    }
}
