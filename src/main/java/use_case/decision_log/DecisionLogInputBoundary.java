package use_case.decision_log;

import entity.Decision;

import java.util.List;

/**
 * Input Boundary for actions which are related to decision log.
 */

public interface DecisionLogInputBoundary {

    /**
     * Executes the decision log use case.
     * @param DecisionLogInputData the decision log data
     */
    void execute(DecisionLogInputData DecisionLogInputData);

    void switchToHomepageView();

    /**
     * Executes the switch to Homepage view use case.
     */
    void switchToHomepageView(DecisionLogInputData DecisionLogInputData);

    void switchToDecisionLogView(DecisionLogInputData inputData);

    List<Decision> getDecisions(String username);

    double getTotalNetWorthChange(String username);

    double getTotalHappinessChange(String username);
}