package interface_adapter.decision_log;

import use_case.decision_log.DecisionLogInputBoundary;
import use_case.decision_log.DecisionLogInputData;

/**
 * Controller for the Decision Log Use Case.
 */
public class DecisionLogController {
    private final DecisionLogInputBoundary decisionLogInteractor;

    public DecisionLogController(DecisionLogInputBoundary decisionLogInteractor) {
        this.decisionLogInteractor = decisionLogInteractor;
    }

    /**
     * Executes navigation to the Decision Log page.
     */
    public void navigateToDecisionLog(String username) {
        DecisionLogInputData inputData = new DecisionLogInputData(username);
        decisionLogInteractor.switchToDecisionLogView(inputData);
    }
}

