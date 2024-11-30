package use_case.game_decision;

import entity.Decision;
import entity.User;
import use_case.settings.*;

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
        GameDecisionOutputBoundary gameDecisionOutputBoundary = new GameDecisionOutputBoundary();
        outputBoundary.prepareAssetsView(gameDecisionOutputBoundary);
    }

    @Override
    public void pickDecision(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        Decision decision = inputData.getDecisionQuestion();
        user.addDecision(decision);
        user.getAssets().changeCash(decision.getCashChange());
        user.changeHappiness(decision.getHappinessChange());
        userDataAccess.updateDecision(user, decision);
        userDataAccess.updateAssets();
        userDataAccess.updateHappiness();
    }

    @Override
    public void switchToBankruptcy(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        GameDecisionOutputData outputData = new GameDecisionOutputData();
        outputBoundary.prepareBankruptcyView();
    }
}
