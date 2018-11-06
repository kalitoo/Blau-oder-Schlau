package com.example.blauoderschlau.contracts;

import com.example.blauoderschlau.model.Game;

import java.util.List;

public interface MainMenuContract {
    interface View {
        /** call to display list with games */
        void showGameHistory(List<Game> games);
        /** call to enable or disable start button */
        void enableStartGameButton(boolean enable);
        /** call to launch quiz view */
        void startQuiz();
    }

    interface Presenter {
        /** call to load initial string */
        void init();
        /** call when user has clicked start button */
        void startButtonClicked();
        /** call when user has selected game from history */
        void gameFromHistoryClicked(int i);
    }
}
