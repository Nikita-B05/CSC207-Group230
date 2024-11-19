package use_case.decision_log;

/**
 * Input Boundary for actions which are related to decision log.
 */

public interface DecisionLogInputBoundary {

    /**
     * Executes the decision log use case.
     * @param DecisionLogInputData the decision log data
     */
    void execute(DecisionLogInputData DecisionLogInputData);

    /**
     * Executes the switch to Homepage view use case.
     */
    void switchToHomepageView();
}