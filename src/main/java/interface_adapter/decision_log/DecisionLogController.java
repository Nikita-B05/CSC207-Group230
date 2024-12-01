package interface_adapter.decision_log;

import use_case.decision_log.DecisionLogInputBoundary;
import use_case.decision_log.DecisionLogInputData;
import use_case.decision_log.DecisionLogOutputBoundary;
import use_case.decision_log.DecisionLogOutputData;
import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInputData;

/**
 * Controller for the Decision Log Use Case.
 */
public class DecisionLogController {
    private final DecisionLogInputBoundary decisionLogInteractor;
    private final DecisionLogOutputBoundary decisionLogPresenter;

    public DecisionLogController(DecisionLogInputBoundary decisionLogInteractor,
                                 DecisionLogOutputBoundary decisionLogPresenter) {
        this.decisionLogInteractor = decisionLogInteractor;
        this.decisionLogPresenter = decisionLogPresenter;
    }

    /**
     * Executes navigation to the Decision Log page.
     */
    public void navigateToDecisionLog(String username) {
        DecisionLogInputData inputData = new DecisionLogInputData(username);

        // Call the interactor to fetch the data
        decisionLogInteractor.execute(inputData);

        // This assumes that the presenter handles the UI update (success/failure view)
        decisionLogInteractor.switchToDecisionLogView(inputData);
    }

    public void switchToHomepageView(String username) {
        DecisionLogInputData DecisionLogInputData = new DecisionLogInputData(username);
        decisionLogInteractor.switchToHomepageView(DecisionLogInputData);
    }

    public void handleSuccess(DecisionLogOutputData outputData) {
        // Pass data from the interactor to the view
        // Assuming the view is updated through this method
        decisionLogPresenter.prepareSuccessView(outputData);
    }

    public void handleFailure(String errorMessage) {
        // Handle failure in fetching or displaying decision log
        decisionLogPresenter.prepareFailView(errorMessage);
    }
}
