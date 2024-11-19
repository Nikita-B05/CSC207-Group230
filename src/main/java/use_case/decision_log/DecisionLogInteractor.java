package use_case.decision_log;

import entity.Decision;
import java.util.List;

/**
 * The Decision Log Interactor.
 */
public class DecisionLogInteractor implements DecisionLogInputBoundary {
    private final DecisionLogDataAccessInterface decisionDataAccessObject;
    private final DecisionLogOutputBoundary decisionLogPresenter;

    public DecisionLogInteractor(DecisionLogDataAccessInterface decisionDataAccessInterface,
                                 DecisionLogOutputBoundary decisionLogOutputBoundary) {
        this.decisionDataAccessObject = decisionDataAccessInterface;
        this.decisionLogPresenter = decisionLogOutputBoundary;
    }

    @Override
    public void execute(DecisionLogInputData decisionLogInputData) {
        // Retrieve username from input data
        String username = decisionLogInputData.getUsername();

        // Fetch decisions for the user
        List<Decision> decisions = decisionDataAccessObject.getDecisions(username);

        if (decisions.isEmpty()) {
            // No decisions found
            decisionLogPresenter.prepareFailView("No decisions found for " + username);
        } else {
            // Prepare the decision log output data
            DecisionLogOutputData outputData = new DecisionLogOutputData(decisions);
            decisionLogPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToHomepageView() {
        // Implement navigation logic to homepage
        decisionLogPresenter.switchToHomePageView();
    }
}
