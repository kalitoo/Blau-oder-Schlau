package com.example.blauoderschlau.logic;

import android.os.Handler;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.contracts.QuizContract;
import com.example.blauoderschlau.model.FakeDataProvider;
import com.example.blauoderschlau.model.QuestionUnit;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class QuizPresenter implements QuizContract.Presenter {

    private QuizContract.View view;
    private DatabaseManagerContract.Model model;

    private List<QuestionUnit> questionUnitBundle;
    // TODO save this index when app is paused
    private int currentQuestionIndex = 0;
    // key: A,B,C,D as displayed in view, value: corresponding index in answer
    private HashMap<QuizContract.EAnswerOption, Integer> answerMapping;

    public QuizPresenter(QuizContract.View view){
        this.view = view;
        this.model = new FakeDataProvider();
        questionUnitBundle = model.getQuestionBundle(0, 10);
        answerMapping = new HashMap<>();
        regenerateRandomHashMap();
        if(questionUnitBundle.isEmpty()) {
            view.showEntireQuestion(null);
        }
        else {
            showQuestion();
        }
    }

    @Override
    public void answerClicked(QuizContract.EAnswerOption pos) {
        QuestionUnit currentQuestionUnit = questionUnitBundle.get(currentQuestionIndex);
        if(currentQuestionUnit.getAnswers()[answerMapping.get(pos)] == currentQuestionUnit.getCorrectAnswer()){
            view.markAnswerAsRight(pos);
        }
        else{
            view.markAnswerAsWrong(pos);
        }
        currentQuestionIndex++;
        // this is just a googled solution to implement time delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.resetAllMarkings();
                if(currentQuestionIndex < questionUnitBundle.size()){
                    showQuestion();
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

    private void regenerateRandomHashMap() {
        List<QuizContract.EAnswerOption> answerOptionsShuffled =
                Arrays.asList(QuizContract.EAnswerOption.values());
        Collections.shuffle(answerOptionsShuffled);
        Integer i = 0;
        for(QuizContract.EAnswerOption answerOption : answerOptionsShuffled){
            answerMapping.put(answerOption, i++);
        }
    }

    private void showQuestion(){
        regenerateRandomHashMap();
        QuestionUnit currentQuestionUnit = questionUnitBundle.get(currentQuestionIndex);
        view.showQuestionString(currentQuestionUnit.getQ());
        for(QuizContract.EAnswerOption answerOption : answerMapping.keySet()) {
            Integer pos = answerMapping.get(answerOption);
            view.showAnswer(currentQuestionUnit.getAnswers()[pos].getText(), answerOption);
        }

    }
}
