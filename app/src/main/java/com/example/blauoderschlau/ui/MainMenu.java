package com.example.blauoderschlau.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.blauoderschlau.R;
import com.example.blauoderschlau.contracts.MainMenuContract;
import com.example.blauoderschlau.database.GameListAdapter;
import com.example.blauoderschlau.database.MyDB;
import com.example.blauoderschlau.logic.MainMenuPresenter;
import com.example.blauoderschlau.model.Game;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenu extends AppCompatActivity implements MainMenuContract.View {


    @BindView(R.id.startButton)
    Button startButton;
    @BindView(R.id.scoreHistory)
    ListView scoreHistory;
    @BindView(R.id.textView1)
    TextView textView1;

    List<Game> gameHistory = new ArrayList();

    MainMenuContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        presenter = new MainMenuPresenter(this,getApplicationContext());

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.startButtonClicked();
            }
        });

        presenter.init();



        //final Cursor cursor = MyDB.getInstance(getApplicationContext()).databaseReadable.query("history",new String[]{"permill","date"},null,null,null,null,null);
        //ListAdapter historyListAdapter =  new GameHistoryCursorAdapter(getApplicationContext(),cursor);

        List<Game> games = MyDB.getInstance(getApplicationContext()).loadAllGamesFromHistory();
        ListAdapter gameListAdapter = new GameListAdapter(this, games);

        scoreHistory.setAdapter(gameListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hauptmenue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showGameHistory(List<Game> games) {
        for(Game game : games){
            gameHistory.add(game);
        }
    }

    @Override
    public void enableStartGameButton(boolean enable) {

    }

    @Override
    public void startQuiz() {
        Intent intent = new Intent(MainMenu.this, QuizActivity.class);
        startActivity(intent);
    }
}
