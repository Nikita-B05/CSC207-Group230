package use_case.decision_log;

/**
 * The output boundary for the DecisionLog Use Case.
 */
public interface DecisionLogOutputBoundary {
    void switchToDecisionLogView(DecisionLogOutputData outputData);

    /**
     * Switches to the Homepage View.
     */

    void switchToHomePageView();

    void prepareFailView(String s);

    void prepareSuccessView(DecisionLogOutputData outputData);

}