package use_case.game_over;

import use_case.game_decision.GameDecisionOutputData;

/**
 * The output boundary for the Game Decision Use Case.
 */
public interface GameOverOutputBoundary {
    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
    void prepareHomepageView(GameOverOutputData outputData);
}
