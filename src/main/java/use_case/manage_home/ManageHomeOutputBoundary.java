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
     */
    void prepareBuySuccessView(String successMessage, double newHome);

    /**
     * Prepares the sell success view for the Manage Home Use Case.
     * @param successMessage the explanation of success
     */
    void prepareSellSuccessView(String successMessage);

    /**
     * Switches to the Asset Manager View.
     * @param outputData the output data
     */
    void switchToAssetManagerView(ManageHomeOutputData outputData);
}
