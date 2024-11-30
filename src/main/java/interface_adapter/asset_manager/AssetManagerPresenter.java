package interface_adapter.asset_manager;

import interface_adapter.ViewManagerModel;
import interface_adapter.manage_home.ManageHomeState;
import interface_adapter.manage_home.ManageHomeViewModel;
import interface_adapter.manage_stock.ManageStockState;
import interface_adapter.manage_stock.ManageStockViewModel;
import use_case.choose_asset.ChooseAssetOutputBoundary;
import use_case.choose_asset.ChooseAssetOutputData;

public class AssetManagerPresenter implements ChooseAssetOutputBoundary {

    private final AssetManagerViewModel assetManagerViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ManageHomeViewModel manageHomeViewModel;
    private final ManageStockViewModel manageStockViewModel;
//    private final GameDecisionViewModel gameDecisionViewModel;

    public AssetManagerPresenter(
            AssetManagerViewModel assetManagerViewModel,
            ViewManagerModel viewManagerModel,
            ManageHomeViewModel manageHomeViewModel,
            ManageStockViewModel manageStockViewModel
//            GameDecisionViewModel
    ) {
        this.assetManagerViewModel = assetManagerViewModel;
        this.viewManagerModel = viewManagerModel;
        this.manageHomeViewModel = manageHomeViewModel;
        this.manageStockViewModel = manageStockViewModel;
    }

    @Override
    public void switchToManageHomeView(ChooseAssetOutputData chooseAssetOutputData) {
        ManageHomeState state = manageHomeViewModel.getState();
        state.setHome(chooseAssetOutputData.getHome());
        state.setDarkMode(chooseAssetOutputData.isDarkMode());
        state.setAvailableCash(chooseAssetOutputData.getCash());
        manageHomeViewModel.setState(state);
        manageHomeViewModel.firePropertyChanged();

        viewManagerModel.setState(manageHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToManageStockView(ChooseAssetOutputData chooseAssetOutputData) {
        ManageStockState state = manageStockViewModel.getState();
        state.setQuantity(null);
        state.setDarkMode(chooseAssetOutputData.isDarkMode());
        state.setNameToCode(chooseAssetOutputData.getNameToCode());
        state.setCodeToPrice(chooseAssetOutputData.getCodeToPrice());
        state.setStockNames(chooseAssetOutputData.getStockNames());
        state.setCode(chooseAssetOutputData.getNameToCode().get(chooseAssetOutputData.getStockNames()[0]));
        state.setCash(chooseAssetOutputData.getCash());
        manageStockViewModel.setState(state);
        manageStockViewModel.firePropertyChanged();

        viewManagerModel.setState(manageStockViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameDecisionView(ChooseAssetOutputData chooseAssetOutputData) {
        // TODO
    }
}
