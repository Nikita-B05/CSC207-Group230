package use_case.game_decision;

import entity.Assets;
import entity.Decision;
import entity.Question;
import entity.User;

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
        GameDecisionOutputData outputData = new GameDecisionOutputData(
                inputData.getUsername(),
                inputData.isDarkMode(),
                inputData.getName(),
                inputData.getAssets(),
                inputData.getAvatar(),
                inputData.getAge(),
                inputData.getHappiness(),
                inputData.getSalary()
        );
        outputBoundary.prepareAssetsView(outputData);
    }

    @Override
    public void pickDecision(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        Question question = inputData.getQuestion();
        Decision decision = inputData.getDecisionQuestion();
        
        // Create new decision with proper order of text and response
        Decision newDecision = new Decision(
            user.getAge(),
            question.getQuestionText(),
            decision.getDecisionText(),
            decision.getNetWorthChange(),
            decision.getHappinessChange(),
            decision.getSalaryChange()
        );
        
        user.addDecision(newDecision);
        user.modifySalary(decision.getSalaryChange());
        user.getAssets().changeCash(decision.getNetWorthChange());
        user.changeHappiness(decision.getHappinessChange());
        userDataAccess.updateSalary(user);
        userDataAccess.updateDecision(user);
        userDataAccess.updateAssets(user);
        userDataAccess.updateHappiness(user);
        userDataAccess.incrementAge();
        userDataAccess.addSalary();
        userDataAccess.appreciateHome();
    }

    @Override
    public void switchToGameOver(GameDecisionInputData inputData) {
        GameDecisionOutputData outputData = new GameDecisionOutputData(inputData.getUsername(), inputData.isDarkMode(),
                inputData.getName(), inputData.getAssets(), inputData.getAvatar(), inputData.getAge(),
                inputData.getHappiness(), inputData.getSalary());
        outputBoundary.prepareGameOverView(outputData);
    }

    @Override
    public void switchToHomeview(GameDecisionInputData inputData) {
        GameDecisionOutputData outputData = new GameDecisionOutputData(inputData.getUsername(), inputData.isDarkMode(),
                inputData.getName(), inputData.getAssets(), inputData.getAvatar(), inputData.getAge(), inputData.getHappiness(), inputData.getSalary());
        outputBoundary.prepareHomepageView(outputData);
    }

    @Override
    public void switchToGameSuccess(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        GameDecisionOutputData outputData = new GameDecisionOutputData(inputData.getUsername(), inputData.isDarkMode(),
                inputData.getName(), inputData.getAssets(), inputData.getAvatar(), inputData.getAge(), inputData.getHappiness(), inputData.getSalary());
        outputBoundary.prepareGameSuccessView(outputData);
    }
}
