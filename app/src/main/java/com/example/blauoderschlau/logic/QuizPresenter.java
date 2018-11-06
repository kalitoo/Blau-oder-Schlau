package com.example.blauoderschlau.logic;

import android.os.Handler;
import android.util.Log;

import com.example.blauoderschlau.contracts.DatabaseManagerContract;
import com.example.blauoderschlau.contracts.QuizContract;
import com.example.blauoderschlau.model.FakeDataProvider;
import com.example.blauoderschlau.model.QuestionResult;
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
    private HashMap<QuizContract.EAnswerOption, Integer> answerMapping = new HashMap<>();
    private HashMap<QuestionUnit, QuestionResult> timeToAnswerMapping = new HashMap<>();

    private long questionStartTimeMillis;
    private boolean answerClicked;

    public QuizPresenter(QuizContract.View view) {
        this.view = view;
        this.model = new FakeDataProvider();
        questionUnitBundle = model.getQuestionBundle(0, 10);
        regenerateRandomHashMap();
        if (questionUnitBundle.isEmpty()) {
            view.showEntireQuestion(null);
        } else {
            showQuestion();
        }
    }

    @Override
    public void answerClicked(QuizContract.EAnswerOption pos) {
        // avoid double click effects
        if (answerClicked) return;
        answerClicked = true;

        QuestionResult questionResult = new QuestionResult();
        questionResult.msToAnswerSelected = getMillisToAnswer();

        QuestionUnit currentQuestionUnit = questionUnitBundle.get(currentQuestionIndex);
        QuizContract.EAnswerOption correctPos = null;
        for (QuizContract.EAnswerOption ao : QuizContract.EAnswerOption.values()) {
            if (currentQuestionUnit.getAnswers()[answerMapping.get(ao)].isCorrect()) {
                correctPos = ao;
            }
        }

        view.markAnswerAsRight(correctPos);
        if (pos != correctPos) {
            // wrong answered
            questionResult.correctAnswerSelected = false;
            view.markAnswerAsWrong(pos);
        } else {
            // correct answered
            questionResult.correctAnswerSelected = true;
        }

        timeToAnswerMapping.put(questionUnitBundle.get(currentQuestionIndex), questionResult);

        currentQuestionIndex++;
        // this is just a googled solution to implement time delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.resetAllMarkings();
                if (currentQuestionIndex < questionUnitBundle.size()) {
                    showQuestion();
                } else {
                    printSomeStuff();
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
        for (QuizContract.EAnswerOption answerOption : answerOptionsShuffled) {
            answerMapping.put(answerOption, i++);
        }
    }

    private void showQuestion() {
        regenerateRandomHashMap();
        answerClicked = false;
        QuestionUnit currentQuestionUnit = questionUnitBundle.get(currentQuestionIndex);
        view.showQuestionString(currentQuestionUnit.getQ());
        for (QuizContract.EAnswerOption answerOption : answerMapping.keySet()) {
            Integer pos = answerMapping.get(answerOption);
            view.showAnswer(currentQuestionUnit.getAnswers()[pos].getText(), answerOption);
            startTimeToAnswerTimer();
        }
    }

    private void startTimeToAnswerTimer() {
        questionStartTimeMillis = System.currentTimeMillis();
    }

    private long getMillisToAnswer() {
        return System.currentTimeMillis() - questionStartTimeMillis;
    }

    private void printSomeStuff() {
        long avTTA = 0;
        int cnt = 0;
        int wrongAnswers = 0;
        for (QuestionResult qr : timeToAnswerMapping.values()) {
            avTTA += qr.msToAnswerSelected;
            wrongAnswers += (qr.correctAnswerSelected ? 0 : 1);
            cnt++;
        }
        avTTA /= (cnt == 0 ? 1 : cnt);
        view.showQuestionString("average time till answer: " + avTTA
                + "\n# wrong answers: " + wrongAnswers + " (" +
                (timeToAnswerMapping.size()) + " total)");
    }
}
