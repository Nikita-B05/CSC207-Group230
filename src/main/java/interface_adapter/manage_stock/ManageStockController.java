package interface_adapter.manage_stock;

import use_case.manage_stock.ManageStockInputBoundary;
import use_case.manage_stock.ManageStockInputData;

/**
 * The controller for the Manage Stock Use Case.
 */
public class ManageStockController {
    private ManageStockInputBoundary manageStockInteractor;

    public ManageStockController(ManageStockInputBoundary manageStockInteractor) {
        this.manageStockInteractor = manageStockInteractor;
    }

    /**
     * Executes the Manage Stock Use Case.
     * @param stockCode the stock code to buy/sell.
     * @param quantity the quantity to be bought/sold.
     * @param isBuying is the user buying or selling.
     */
    public void execute(String stockCode, String quantity, boolean isBuying) {
        final ManageStockInputData inputData = new ManageStockInputData(stockCode, quantity, isBuying);
        manageStockInteractor.execute(inputData);
    }

    /**
     * Switches to Asset Manager View.
     */
    public void switchToAssetManagerView() {
        manageStockInteractor.switchToAssetManagerView();
    }
}
