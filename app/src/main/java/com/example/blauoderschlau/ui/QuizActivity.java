package com.example.blauoderschlau.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.blauoderschlau.R;
import com.example.blauoderschlau.contracts.QuizContract;
import com.example.blauoderschlau.logic.QuizPresenter;
import com.example.blauoderschlau.model.Answer;
import com.example.blauoderschlau.model.QuestionUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity implements QuizContract.View {

    private QuizContract.Presenter presenter;

    @BindView(R.id.button)
    Button buttonA;
    @BindView(R.id.button5)
    Button buttonB;
    @BindView(R.id.button6)
    Button buttonC;
    @BindView(R.id.button7)
    Button buttonD;
    @BindView(R.id.questionView)
    TextView questionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        // important: call this only after butterknife binding or view elements will be null
        presenter = new QuizPresenter(this);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.answerClicked(QuizContract.EAnswerOption.A);
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.answerClicked(QuizContract.EAnswerOption.B);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.answerClicked(QuizContract.EAnswerOption.C);
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.answerClicked(QuizContract.EAnswerOption.D);
            }
        });
    }


    @Override
    public void markAnswerAsRight(QuizContract.EAnswerOption pos) {

        switch(pos){
            case A: buttonA.setBackgroundColor(Color.GREEN); break;
            case B: buttonB.setBackgroundColor(Color.GREEN); break;
            case C: buttonC.setBackgroundColor(Color.GREEN); break;
            case D: buttonD.setBackgroundColor(Color.GREEN); break;
            default: break;
        }
    }

    @Override
    public void markAnswerAsWrong(QuizContract.EAnswerOption pos) {
        switch(pos) {
            case A: buttonA.setBackgroundColor(Color.RED); break;
            case B: buttonB.setBackgroundColor(Color.RED); break;
            case C: buttonC.setBackgroundColor(Color.RED); break;
            case D: buttonD.setBackgroundColor(Color.RED); break;
            default: break;
        }
    }

    @Override
    public void showEntireQuestion(QuestionUnit q) {

    }

    @Override
    public void showQuestionString(String q) {
        questionView.setText(q);
    }

    @Override
    public void showAnswer(String a, QuizContract.EAnswerOption pos) {
        switch (pos){
            case A: buttonA.setText(a); break;
            case B: buttonB.setText(a); break;
            case C: buttonC.setText(a); break;
            case D: buttonD.setText(a); break;
            default: break;
        }
    }

    @Override
    public void resetAllMarkings() {
        buttonA.setBackgroundResource(android.R.drawable.btn_default);
        buttonB.setBackgroundResource(android.R.drawable.btn_default);
        buttonC.setBackgroundResource(android.R.drawable.btn_default);
        buttonD.setBackgroundResource(android.R.drawable.btn_default);
    }

    @Override
    public void lastQuestionAnswered() {
        // switch to score activity
    }

}
