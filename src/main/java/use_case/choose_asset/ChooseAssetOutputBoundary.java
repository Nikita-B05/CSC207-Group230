package use_case.choose_asset;

/**
 * Output Boundary for actions which are related to choosing asset.
 */
public interface ChooseAssetOutputBoundary {

    /**
     * Switches to Manage Home View.
     * @param chooseAssetOutputData the input data
     */
    void switchToManageHomeView(ChooseAssetOutputData chooseAssetOutputData);

    /**
     * Switches to Manage Stock View.
     * @param chooseAssetOutputData the input data
     */
    void switchToManageStockView(ChooseAssetOutputData chooseAssetOutputData);

    /**
     * Switches to Game Decision View.
     * @param chooseAssetOutputData the input data
     */
    void switchToGameDecisionView(ChooseAssetOutputData chooseAssetOutputData);
}
