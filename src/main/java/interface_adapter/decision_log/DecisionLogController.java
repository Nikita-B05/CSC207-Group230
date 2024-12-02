package interface_adapter.decision_log;

import entity.Decision;
import use_case.decision_log.DecisionLogInputBoundary;
import use_case.decision_log.DecisionLogInputData;
import use_case.decision_log.DecisionLogOutputBoundary;
import use_case.decision_log.DecisionLogOutputData;
import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInputData;
import view.DecisionLogView;

import java.util.List;
import java.util.Map;

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
