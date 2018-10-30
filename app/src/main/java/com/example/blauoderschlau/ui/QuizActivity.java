package com.example.blauoderschlau.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.blauoderschlau.R;
import com.example.blauoderschlau.contracts.QuizContract;
import com.example.blauoderschlau.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity implements QuizContract.View {

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


    }


    @Override
    public void markAnswerAsRight(int pos) {

    }

    @Override
    public void markAnswerAsWrong(int pos) {

    }

    @Override
    public void showQuestion(Question q) {

    }
}
