package interface_adapter.asset_manager;

import interface_adapter.manage_stock.ManageStockState;
import use_case.choose_asset.ChooseAssetInputBoundary;
import use_case.choose_asset.ChooseAssetOutputData;

public class AssetManagerController {
    private final ChooseAssetInputBoundary chooseAssetInteractor;

    public AssetManagerController(ChooseAssetInputBoundary chooseAssetInteractor) {
        this.chooseAssetInteractor = chooseAssetInteractor;
    }

    public void switchToManageHomeView() {
        chooseAssetInteractor.switchToManageHomeView();
    }

    public void switchToManageStockView() {
        chooseAssetInteractor.switchToManageStockView();
    }

    public void switchToGameDecisionView() {
        // TODO
    }
}
