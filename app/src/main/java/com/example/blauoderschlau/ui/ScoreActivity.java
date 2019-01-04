package com.example.blauoderschlau.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    ScoreContract.Presenter presenter;

    private int prograssBarStatus = 0;

    private Handler handler = new Handler();

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
        int postComma = (int) ((perMill - preComma) * 100);
        scoreTextView.setText("HERLZICHEN GLÜCKWUNSCH!"+"\nDu hast " + preComma
                + "." + postComma + " Promille.");

        //3 permill as maximun
        final double percentage = (100 * perMill)/3;

        //new thread so UI does not freeze
         new Thread(new Runnable()
         {
             @Override
             public void run()
             {
                 while ( prograssBarStatus < percentage)
                 {
                     prograssBarStatus++;
                     android.os.SystemClock.sleep(20);

                     //communicating back with UI
                     handler.post(new Runnable()
                     {
                         @Override
                         public void run()
                         {
                             progressBar.setProgress(prograssBarStatus);
                         }
                     });
                 }
             }
         }).start();

    }

    @Override
    public void goToMainMenu() {
        Intent intent = new Intent(this, MainMenu.class);
        // clear preceding activities
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
