package interface_adapter.game_over;

import entity.*;
import use_case.game_over.GameOverInputData;
import use_case.game_over.GameOverInputBoundary;

public class GameOverController {

    private final GameOverInputBoundary gameOverInteractor;


    public GameOverController(GameOverInputBoundary gameOverInteractor) {
        this.gameOverInteractor = gameOverInteractor;
    }

    public void switchToHomepage(Assets assets, int happiness, Avatar avatar, boolean darkMode, int age, String characterName, String username) {
        final GameOverInputData inputData = new GameOverInputData(assets, happiness, avatar, darkMode, age, characterName, username);
        gameOverInteractor.switchToHomeview(inputData);
    }

}
