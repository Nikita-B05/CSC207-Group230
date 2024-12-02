package interface_adapter.manage_stock;

import interface_adapter.ViewManagerModel;
import interface_adapter.asset_manager.AssetManagerState;
import interface_adapter.asset_manager.AssetManagerViewModel;
import use_case.manage_stock.ManageStockOutputBoundary;
import use_case.manage_stock.ManageStockOutputData;

/**
 * The Presenter for the Manage Stock Use Case.
 */
public class ManageStockPresenter implements ManageStockOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ManageStockViewModel manageStockViewModel;
    private final AssetManagerViewModel assetManagerViewModel;

    public ManageStockPresenter(
            ViewManagerModel viewManagerModel,
            ManageStockViewModel manageStockViewModel,
            AssetManagerViewModel assetManagerViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.manageStockViewModel = manageStockViewModel;
        this.assetManagerViewModel = assetManagerViewModel;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        ManageStockState state = manageStockViewModel.getState();
        state.setErrorMessage(errorMessage);
        manageStockViewModel.firePropertyChanged("manageStockError");
    }

    @Override
    public void prepareBuySuccessView(String successMessage, double cash) {
        ManageStockState manageStockState = manageStockViewModel.getState();
        manageStockState.setCash(cash);
        manageStockState.setSuccessMessage(successMessage);
        manageStockViewModel.firePropertyChanged("manageStockBuySuccess");
    }

    @Override
    public void prepareSellSuccessView(String successMessage, double cash) {
        ManageStockState manageStockState = manageStockViewModel.getState();
        manageStockState.setCash(cash);
        manageStockState.setSuccessMessage(successMessage);
        manageStockViewModel.firePropertyChanged("manageStockSellSuccess");
    }

    @Override
    public void switchToAssetManagerView(ManageStockOutputData outputData) {
        viewManagerModel.setState(assetManagerViewModel.getViewName());
        final AssetManagerState state = assetManagerViewModel.getState();
        state.setDarkMode(outputData.isDarkMode());
        assetManagerViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
