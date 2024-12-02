package interface_adapter.game_over;

import use_case.game_over.GameOverInputBoundary;
import use_case.game_over.GameOverInputData;

import javax.swing.*;

public class GameOverController {
    private final GameOverInputBoundary gameOverInteractor;

    public GameOverController(GameOverInputBoundary gameOverInteractor) {
        this.gameOverInteractor = gameOverInteractor;
    }

    public void execute(String username) {
        GameOverInputData inputData = new GameOverInputData(false, username);
        gameOverInteractor.execute(inputData);
    }

    public void cleanupPlayerData(String username) {
        gameOverInteractor.cleanupPlayerData(username);
    }
}
