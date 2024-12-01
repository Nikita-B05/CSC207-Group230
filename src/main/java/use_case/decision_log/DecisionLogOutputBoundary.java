package use_case.decision_log;

import use_case.homepage.HomepageOutputData;

/**
 * The output boundary for the DecisionLog Use Case.
 */
public interface DecisionLogOutputBoundary {
    void switchToDecisionLogView(DecisionLogOutputData outputData);

    void prepareFailView(String s);

    void prepareSuccessView(DecisionLogOutputData outputData);

    void switchToHomepageView();
}