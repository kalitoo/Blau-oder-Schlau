package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;

public interface MainMenuContract {
    interface View {
        void showGameHistory(Game[] games);
        void enableStartGameButton(boolean enable);
    }
    interface Presenter {
        void startButtonClicked();
        void gameFromHistoryClicked(int i);
    }
}
