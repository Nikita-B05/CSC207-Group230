package use_case.manage_home;

/**
 * The output boundary for the Manage Home Use Case.
 */
public interface ManageHomeOutputBoundary {

    /**
     * Prepares the failure view for the Manage Home Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the buy success view for the Manage Home Use Case.
     * @param successMessage the explanation of success
     * @param newHome value of the bought home
     * @param cash cash the user has
     */
    void prepareBuySuccessView(String successMessage, double newHome, double cash);

    /**
     * Prepares the sell success view for the Manage Home Use Case.
     * @param successMessage the explanation of success
     * @param cash cash the user has
     */
    void prepareSellSuccessView(String successMessage, double cash);

    /**
     * Switches to the Asset Manager View.
     * @param outputData the output data
     */
    void switchToAssetManagerView(ManageHomeOutputData outputData);
}
