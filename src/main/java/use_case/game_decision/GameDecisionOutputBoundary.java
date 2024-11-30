package use_case.game_decision;

/**
 * The output boundary for the Game Decision Use Case.
 */
public interface GameDecisionOutputBoundary {
    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
    void prepareAssetsView(GameDecisionOutputData outputData);
    void prepareGameOverView(GameDecisionOutputData outputData);

}
