package use_case.decision_log;

/**
 * The output boundary for the DecisionLog Use Case.
 */
public interface DecisionLogOutputBoundary {
    /**
     * Switches to the Homepage View.
     */

    void switchToHomePageView();

    void prepareFailView(String s);

    <DecisionLogOutputData> void prepareSuccessView(DecisionLogOutputData outputData);
}