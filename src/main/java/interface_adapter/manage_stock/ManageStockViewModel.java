package interface_adapter.manage_stock;

import interface_adapter.ViewModel;

/**
 * The View Model for the Manage Stock View.
 */
public class ManageStockViewModel extends ViewModel<ManageStockState> {
    public final static String TITLE = "Buy/Sell Stock";

    public final static String BACK_LABEL = "Back";
    public final static String BUY_LABEL = "Buy";
    public final static String SELL_LABEL = "Sell";

    public final static String CHOOSE_STOCK_LABEL = "Choose Stock";
    public final static String QUANTITY_LABEL = "Quantity";

    public ManageStockViewModel() {
        super("manageStock");
        setState(new ManageStockState());
    }
}
