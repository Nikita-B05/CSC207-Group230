package interface_adapter.decision_log;

import use_case.decision_log.DecisionLogInputBoundary;

/**
 * Controller for the Decision Log Use Case.
 */
public class DecisionLogController {
    private final DecisionLogInputBoundary decisionLogInteractor;

    public DecisionLogController(DecisionLogInputBoundary decisionLogInteractor) {
        this.decisionLogInteractor = decisionLogInteractor;
    }

    public void switchToHomepageView() {
        decisionLogInteractor.switchToHomepageView();
    }
}
