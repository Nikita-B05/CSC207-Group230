package use_case.manage_stock;

import entity.Assets;
import entity.Stock;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The Manage Stock Interactor.
 */
public class ManageStockInteractor implements ManageStockInputBoundary {

    private final ManageStockDataAccessInterface userDataAccessObject;
    private final ManageStockStockAccessInterface stockDataAccessObject;
    private final ManageStockOutputBoundary manageStockPresenter;

    public ManageStockInteractor(ManageStockDataAccessInterface userDataAccessInterface,
                                 ManageStockStockAccessInterface stockDataAccessInterface,
                                 ManageStockOutputBoundary manageStockPresenter)
    {
        this.userDataAccessObject = userDataAccessInterface;
        this.stockDataAccessObject = stockDataAccessInterface;
        this.manageStockPresenter = manageStockPresenter;
    }

    @Override
    public void execute(ManageStockInputData manageStockInputData) {
        User user = userDataAccessObject.getCurrentUser();
        int quantity;
        String stockCode = manageStockInputData.getStockCode();
        try {
            quantity = Integer.parseInt(manageStockInputData.getQuantity());
        } catch (NumberFormatException e) {
            manageStockPresenter.prepareFailView("Please enter a number for the quantity.");
            return;
        }
        if (quantity < 1 || quantity > 1_000) {
            manageStockPresenter.prepareFailView("Please enter a quantity between 1 and 1,000.");
            return;
        }
        Map<String, Double> stockPrices = stockDataAccessObject.getCodeToPrice();
        Assets assets = user.getAssets();
        Map<String, String> codeToName = stockDataAccessObject.getCodeToName();
        if (manageStockInputData.isBuying()) {
            if (assets.canBuyStock(stockCode, quantity, stockPrices)) {
                assets.buyStock(stockCode, quantity, stockPrices.get(stockCode));
                userDataAccessObject.updateAssets(assets);
                user = userDataAccessObject.getCurrentUser();
                manageStockPresenter.prepareBuySuccessView(
                "Successfully bought " + quantity + " shares of " + codeToName.get(stockCode) + ".",
                        user.getAssets().getCash()
                );
            }
            else {
                manageStockPresenter.prepareFailView("You do not have enough money to buy stock.");
            }
        }
        else {
            if (assets.isValidSell(stockCode, quantity)) {
                assets.sellStock(stockCode, quantity, stockPrices.get(stockCode));
                userDataAccessObject.updateAssets(assets);
                user = userDataAccessObject.getCurrentUser();
                manageStockPresenter.prepareSellSuccessView(
                    "Successfully sold " + quantity + " shares of " + codeToName.get(stockCode) + ".",
                        user.getAssets().getCash()
                );
            }
            else {
                manageStockPresenter.prepareFailView("You do not have enough shares to sell.");
            }
        }
    }

    @Override
    public void switchToAssetManagerView() {
        User user = userDataAccessObject.getCurrentUser();
        ManageStockOutputData outputData = new ManageStockOutputData(user.isDarkMode());
        manageStockPresenter.switchToAssetManagerView(outputData);
    }
}
