package use_case.manage_stock;

import use_case.logout.LogoutInputData;

/**
 * Input Boundary for actions which are related to managing stock in.
 */
public interface ManageStockInputBoundary {

    /**
     * Executes the Logout use case.
     * @param manageStockInputData the input data
     */
    void execute(ManageStockInputData manageStockInputData);

    /**
     * Switched to Asset Manager view.
     */
    void switchToAssetManagerView();
}
