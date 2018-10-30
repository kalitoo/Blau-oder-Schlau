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
import com.example.blauoderschlau.model.Question;

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
                presenter.answerClicked(0);
            }
        });
    }


    @Override
    public void markAnswerAsRight(int pos) {

        switch(pos){
            case 0: buttonA.setBackgroundColor(Color.GREEN); break;
            case 1: buttonB.setBackgroundColor(Color.GREEN); break;
            //..
            default: break;
        }
    }

    @Override
    public void markAnswerAsWrong(int pos) {
        switch(pos) {
            case 0:
                buttonA.setBackgroundColor(Color.RED);
                break;
            case 1:
                buttonB.setBackgroundColor(Color.RED);
                break;
            //..
            default:
                break;
        }
    }

    @Override
    public void showQuestion(Question q) {
        questionView.setText(q.getQ());
        buttonA.setText(q.getAnswerOptions()[0]);
        buttonB.setText(q.getAnswerOptions()[1]);
        buttonC.setText(q.getAnswerOptions()[2]);
        buttonD.setText(q.getAnswerOptions()[3]);
    }

    @Override
    public void lastQuestionAnswered() {
        // switch to score activity
    }
}
