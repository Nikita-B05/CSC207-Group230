package use_case.game_decision;

import entity.Assets;
import entity.Decision;
import entity.Question;
import entity.User;
import use_case.choose_asset.ChooseAssetStockDataAccessInterface;

/**
 * Interactor for the Settings use case.
 */
public class GameDecisionInteractor implements GameDecisionInputBoundary {
    private final GameDecisionUserDataAccessInterface userDataAccess;
    private final GameDecisionOutputBoundary outputBoundary;
    private final ChooseAssetStockDataAccessInterface stockDataAccessObject;

    public GameDecisionInteractor(GameDecisionUserDataAccessInterface userDataAccess,
                                  GameDecisionOutputBoundary outputBoundary,
                                  ChooseAssetStockDataAccessInterface stockDataAccessInterface) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
        this.stockDataAccessObject = stockDataAccessInterface;

    }

    @Override
    public void switchToAssetsManager(GameDecisionInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        stockDataAccessObject.setDate(user.getAge());
        Question question = inputData.getQuestion();
        Decision decision = inputData.getDecisionQuestion();

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
        GameDecisionOutputData outputData = new GameDecisionOutputData(
                inputData.getUsername(),
                inputData.isDarkMode(),
                inputData.getName(),
                inputData.getAssets(),
                inputData.getAvatar(),
                inputData.getAge(),
                inputData.getHappiness(),
                inputData.getSalary(),
                stockDataAccessObject.getCodeToPrice()
        );
        outputBoundary.prepareAssetsView(outputData);
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
        stockDataAccessObject.setDate(user.getAge());
        GameDecisionOutputData outputData = new GameDecisionOutputData(inputData.getUsername(), inputData.isDarkMode(),
                inputData.getName(), inputData.getAssets(), inputData.getAvatar(), inputData.getAge(), inputData.getHappiness(), inputData.getSalary(), stockDataAccessObject.getCodeToPrice());
        outputBoundary.prepareGameSuccessView(outputData);
    }
}
