package com.example.blauoderschlau.logic;

import android.os.Handler;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.contracts.QuizContract;
import com.example.blauoderschlau.model.FakeDataProvider;
import com.example.blauoderschlau.model.Question;

import java.util.List;

public class QuizPresenter implements QuizContract.Presenter {

    private QuizContract.View view;
    private DatabaseManagerContract.Model model;

    private List<Question> questionBundle;
    private int currentQuestionIndex = 0;

    public QuizPresenter(QuizContract.View view){
        this.view = view;
        this.model = new FakeDataProvider();
        questionBundle = model.getQuestionBundle(0, 10);
        if(questionBundle.isEmpty()) {
            view.showQuestion(null);
        }
        else {
            view.showQuestion(questionBundle.get(0));
        }
    }

    @Override
    public void answerClicked(int pos) {
        view.markAnswerAsWrong(0);
        currentQuestionIndex++;
        // this is just a googled solution to implement time delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.resetAllMarkings();
                if(currentQuestionIndex < questionBundle.size()){
                    view.showQuestion(questionBundle.get(currentQuestionIndex));

                }
                else {
                    view.lastQuestionAnswered();
                }
            }
        }, 1000);
    }

    @Override
    public void aborted() {
        // not implemented yet
    }
}
