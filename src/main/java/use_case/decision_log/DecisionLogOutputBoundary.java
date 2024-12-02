package use_case.decision_log;;

/**
 * The output boundary for the DecisionLog Use Case.
 */
public interface DecisionLogOutputBoundary {

    /**
     * Switches to Manage Home View.
     * @param decisionLogOutputData the input data
     */
    void switchToHomepageView(DecisionLogOutputData decisionLogOutputData);

}