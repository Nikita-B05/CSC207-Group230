package use_case.game_decision;

import use_case.change_password.ChangePasswordOutputData;

/**
 * The output boundary for the Game Decision Use Case.
 */
public interface GameDecisionOutputBoundary {
    /**
     * Prepares the success view for the Change Password Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChangePasswordOutputData outputData);

    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void prepareAssetsView();
    void prepareBankruptcyView();

}
