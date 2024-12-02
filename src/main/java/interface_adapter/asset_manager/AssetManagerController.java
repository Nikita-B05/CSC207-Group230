package interface_adapter.asset_manager;

import use_case.choose_asset.ChooseAssetInputBoundary;

/**
 * The AssetManagerController is responsible for managing the transitions
 * between different views in the asset management process.
 */
public class AssetManagerController {
    private final ChooseAssetInputBoundary chooseAssetInteractor;

    /**
     * Constructs an AssetManagerController with the given ChooseAssetInputBoundary.
     *
     * @param chooseAssetInteractor The interactor used to handle asset choices
     */
    public AssetManagerController(ChooseAssetInputBoundary chooseAssetInteractor) {
        this.chooseAssetInteractor = chooseAssetInteractor;
    }

    /**
     * Switches to the ManageHome view.
     */
    public void switchToManageHomeView() {
        chooseAssetInteractor.switchToManageHomeView();
    }

    /**
     * Switches to the ManageStock view.
     */
    public void switchToManageStockView() {
        chooseAssetInteractor.switchToManageStockView();
    }

    /**
     * Switches to the GameDecision view.
     */
    public void switchToGameDecisionView() {
        chooseAssetInteractor.switchToGameDecisionView();
    }
}
