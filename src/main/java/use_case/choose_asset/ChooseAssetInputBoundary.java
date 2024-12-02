package use_case.choose_asset;

/**
 * Input Boundary for actions which are related to choosing asset.
 */
public interface ChooseAssetInputBoundary {

    /**
     * Switches to Manage Home View.
     */
    void switchToManageHomeView();

    /**
     * Switches to Manage Stock View.
     */
    void switchToManageStockView();

    /**
     * Switches to Game Decision View.
     */
    void switchToGameDecisionView();
}
