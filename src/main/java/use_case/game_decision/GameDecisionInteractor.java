package use_case.game_decision;

import entity.Decision;
import entity.User;
import use_case.settings.SettingsOutputData;

/**
 * Interactor for the Settings use case.
 */
public class GameDecisionInteractor implements GameDecisionInputBoundary {
    private final GameDecisionUserDataAccessInterface userDataAccess;
    private final GameDecisionOutputBoundary outputBoundary;

    public GameDecisionInteractor(GameDecisionUserDataAccessInterface userDataAccess,
                                  GameDecisionOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void switchToAssetsManager(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        GameDecisionOutputData outputData = new GameDecisionOutputData(inputData.getUsername(), inputData.isDarkMode(),
                inputData.getName(), inputData.getAssets(), inputData.getAvatar(), inputData.getAge());
        outputBoundary.prepareAssetsView(outputData);
    }

    @Override
    public void pickDecision(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        Decision decision = inputData.getDecisionQuestion();
        user.addDecision(decision);
        user.modifySalary(decision.getSalaryChange());
        user.getAssets().changeCash(decision.getCashChange());
        user.changeHappiness(decision.getHappinessChange());
        userDataAccess.updateSalary(user);
        userDataAccess.updateDecision(user);
        userDataAccess.updateAssets(user);
        userDataAccess.updateHappiness(user);
    }

    @Override
    public void switchToGameOver(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        GameDecisionOutputData outputData = new GameDecisionOutputData(inputData.getUsername(), inputData.isDarkMode(),
                inputData.getName(), inputData.getAssets(), inputData.getAvatar(), inputData.getAge());
        outputBoundary.prepareGameOverView(outputData);
    }

    @Override
    public void switchToHomeview(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        GameDecisionOutputData outputData = new GameDecisionOutputData(inputData.getUsername(), inputData.isDarkMode(),
                inputData.getName(), inputData.getAssets(), inputData.getAvatar(), inputData.getAge());
        outputBoundary.prepareHomepageView(outputData);

    }
}
