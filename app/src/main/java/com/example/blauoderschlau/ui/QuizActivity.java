package com.example.blauoderschlau.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.blauoderschlau.R;
import com.example.blauoderschlau.contracts.QuizContract;
import com.example.blauoderschlau.logic.QuizPresenter;
import com.example.blauoderschlau.model.QuestionResult;
import com.example.blauoderschlau.model.QuestionUnit;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity implements QuizContract.View {

    private QuizContract.Presenter presenter;

    @BindView(R.id.button)
    Button buttonA;
    @BindView(R.id.button1)
    Button buttonB;
    @BindView(R.id.button2)
    Button buttonC;
    @BindView(R.id.button3)
    Button buttonD;
    @BindView(R.id.questionView)
    TextView questionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        // important: call this only after butterknife binding or view elements will be null
        presenter = new QuizPresenter(this,getApplicationContext());

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
            case A: buttonA.setBackgroundResource(R.drawable.button_right); break;
            case B: buttonB.setBackgroundResource(R.drawable.button_right); break;
            case C: buttonC.setBackgroundResource(R.drawable.button_right); break;
            case D: buttonD.setBackgroundResource(R.drawable.button_right); break;
            default: break;
        }
    }

    @Override
    public void markAnswerAsWrong(QuizContract.EAnswerOption pos) {
        switch(pos) {
            case A: buttonA.setBackgroundResource(R.drawable.button_false); break;
            case B: buttonB.setBackgroundResource(R.drawable.button_false); break;
            case C: buttonC.setBackgroundResource(R.drawable.button_false); break;
            case D: buttonD.setBackgroundResource(R.drawable.button_false); break;
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
        buttonA.setBackgroundResource(R.drawable.button);
        buttonB.setBackgroundResource(R.drawable.button);
        buttonC.setBackgroundResource(R.drawable.button);
        buttonD.setBackgroundResource(R.drawable.button);
    }

    @Override
    public void lastQuestionAnswered(Collection<QuestionResult> questionResultCollection) {
        // switch to score activity
        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
        ArrayList<QuestionResult> questionResultArrayList = new ArrayList<>(questionResultCollection);
        intent.putParcelableArrayListExtra("QuestionResultList", questionResultArrayList);
        startActivity(intent);
    }

}
