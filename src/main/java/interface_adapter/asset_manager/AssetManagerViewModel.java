package interface_adapter.asset_manager;

import interface_adapter.ViewModel;

/**
 * The View Model for the Asset Manager View.
 */
public class AssetManagerViewModel extends ViewModel<AssetManagerState> {
    public static final String TITLE = "Asset Manager";
    public static final String MANAGE_STOCK_LABEL = "Buy/Sell Stock";
    public static final String MANAGE_HOME_LABEL = "Buy/Sell Home";
    public static final String DONE_LABEL = "Done";

    public AssetManagerViewModel() {
        super("assetManager");
        setState(new AssetManagerState());
    }
}
