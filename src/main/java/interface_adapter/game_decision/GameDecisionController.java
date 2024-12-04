package interface_adapter.game_decision;

import entity.*;
import use_case.game_decision.GameDecisionInputData;
import use_case.game_decision.GameDecisionInputBoundary;

/**
 * Controller for the Settings use case.
 */
public class GameDecisionController {
    private final GameDecisionInputBoundary gameDecisionInteractor;

    public GameDecisionController(GameDecisionInputBoundary gameDecisionInteractor) {
        this.gameDecisionInteractor = gameDecisionInteractor;
    }

    public void pickDecision(
            String username,
            int age,
            String characterName,
            boolean darkMode,
            Question question,
            Assets assets,
            Avatar avatar,
            Decision decision,
            int happiness,
            double salary
    ) {
        final GameDecisionInputData inputData =
                new GameDecisionInputData(
                        username, age, characterName, darkMode, question, assets, avatar, decision, happiness, salary);
        gameDecisionInteractor.switchToAssetsManager(inputData);
    }

    public void switchToHomepage(String username, int age, String characterName, boolean darkMode, Question question,
                                 Assets assets, Avatar avatar, Decision decision, int happiness, double salary) {
        final GameDecisionInputData inputData = new GameDecisionInputData(username, age, characterName, darkMode,
                question, assets, avatar, decision, happiness, salary);
        gameDecisionInteractor.switchToHomeview(inputData);
    }

    public void switchToGameOver(String username, int age, String characterName, boolean darkMode, Question question,
                                 Assets assets, Avatar avatar, Decision decision, int happiness, double salary) {
        final GameDecisionInputData inputData = new GameDecisionInputData(username, age, characterName, darkMode,
                question, assets, avatar, decision, happiness, salary);
        gameDecisionInteractor.switchToGameOver(inputData);
    }

    public void switchToGameSuccess(String username, int age, String characterName, boolean darkMode, Question question,
                                    Assets assets, Avatar avatar, Decision decision, int happiness, double salary) {
        final GameDecisionInputData inputData = new GameDecisionInputData(username, age, characterName, darkMode,
                question, assets, avatar, decision, happiness, salary);
        gameDecisionInteractor.switchToGameSuccess(inputData);
    }
}
