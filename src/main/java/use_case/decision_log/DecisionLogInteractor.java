package use_case.decision_log;

import entity.Decision;
import entity.User;

import java.util.List;

/**
 * The Decision Log Interactor.
 */
public class DecisionLogInteractor implements DecisionLogInputBoundary {
    private final DecisionLogUserDataAccessInterface userDataAccessObject;
    private final DecisionLogOutputBoundary decisionLogPresenter;

    public DecisionLogInteractor(
            DecisionLogUserDataAccessInterface userDataAccessInterface,
            DecisionLogOutputBoundary decisionLogOutputBoundary
    ) {
        this.userDataAccessObject = userDataAccessInterface;
        this.decisionLogPresenter = decisionLogOutputBoundary;
    }

    @Override
    public void switchToHomepageView() {
        User user = userDataAccessObject.getCurrentUser();
        List<Decision> decisions = user.getDecisions();
        
        DecisionLogOutputData outputData = new DecisionLogOutputData(
            user.getUsername(),
            decisions,
            user.isDarkMode()
        );
        
        decisionLogPresenter.switchToHomepageView(outputData);
    }
}
