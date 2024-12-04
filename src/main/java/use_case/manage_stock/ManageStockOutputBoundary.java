package use_case.manage_stock;

/**
 * The output boundary for the Manage Stock Use Case.
 */
public interface ManageStockOutputBoundary {

    /**
     * Prepares the failure view for the Manage Stock Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the buy success view for the Manage Stock Use Case.
     * @param successMessage the explanation of success
     * @param cash the new cash the user has
     */
    void prepareBuySuccessView(String successMessage, double cash);

    /**
     * Prepares the sell success view for the Manage Stock Use Case.
     * @param successMessage the explanation of success
     * @param cash the new cash the user has
     */
    void prepareSellSuccessView(String successMessage, double cash);

    /**
     * Switches to the Asset Manager View.
     * @param outputData the output data
     */
    void switchToAssetManagerView(ManageStockOutputData outputData);
}
