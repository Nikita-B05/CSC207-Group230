package interface_adapter.manage_stock;

import interface_adapter.ViewModel;

/**
 * The View Model for the Manage Stock View.
 */
public class ManageStockViewModel extends ViewModel<ManageStockState> {
    public static final String TITLE = "Buy/Sell Stock";

    public static final String BACK_LABEL = "Back";
    public static final String BUY_LABEL = "Buy";
    public static final String SELL_LABEL = "Sell";

    public static final String CHOOSE_STOCK_LABEL = "Choose Stock";
    public static final String QUANTITY_LABEL = "Quantity";

    public ManageStockViewModel() {
        super("manageStock");
        setState(new ManageStockState());
    }
}
