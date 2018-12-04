package com.example.blauoderschlau.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.blauoderschlau.R;
import com.example.blauoderschlau.contracts.ScoreContract;
import com.example.blauoderschlau.logic.ScorePresenter;
import com.example.blauoderschlau.model.Game;
import com.example.blauoderschlau.model.QuestionResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity implements ScoreContract.View{


    @BindView(R.id.scoreButton)
    Button scoreButton;
    @BindView(R.id.scoreTextView)
    TextView scoreTextView;

    ScoreContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);

        presenter = new ScorePresenter();

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.returnToHome();
            }
        });

        showResults((Game) getIntent().getSerializableExtra("Game"));
    }

    @Override
    public void showResults(Game game)
    {
        scoreTextView.setText("HERLZICHEN GLÜCKWUNSCH!"+"\nDu hast " + Integer.toString(game.getScore())
                + " Promille.");
    }
}
