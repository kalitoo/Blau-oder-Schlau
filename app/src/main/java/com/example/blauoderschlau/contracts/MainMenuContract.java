package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;

import java.util.List;

public interface MainMenuContract {
    interface View {
        void showGameHistory(List<Game> games);

        void enableStartGameButton(boolean enable);

        void startQuiz();
    }

    interface Presenter {
        void init();

        void startButtonClicked();

        void gameFromHistoryClicked(int i);
    }
}
