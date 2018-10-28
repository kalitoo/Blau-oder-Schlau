package com.example.jojod.drunkquiz.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jojod.drunkquiz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity {

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




}
