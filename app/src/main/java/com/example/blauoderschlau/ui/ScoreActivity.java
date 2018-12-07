package com.example.blauoderschlau.ui;

import android.content.Intent;
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

import java.util.List;

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

        presenter = new ScorePresenter(this);

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.returnToHome();
            }
        });

        List<QuestionResult> questionResultList =
                getIntent().getParcelableArrayListExtra("QuestionResultList");
        presenter.buildAndSaveGame(questionResultList);
    }

    // TODO write test
    @Override
    public void showResults(Game game)
    {
        final double perMill = game.getPerMill();
        int preComma = (int) perMill;
        int postComma = (int) ((perMill - preComma) * 10 + 0.5);
        scoreTextView.setText("HERLZICHEN GLÜCKWUNSCH!"+"\nDu hast " + Integer.toString(preComma)
                + "." + postComma + " Promille.");
    }

    @Override
    public void goToMainMenu() {
        Intent intent = new Intent(this, MainMenu.class);
        // clear preceding activities
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
