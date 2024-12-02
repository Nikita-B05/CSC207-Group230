package interface_adapter.manage_home;

import interface_adapter.ViewModel;
import interface_adapter.manage_stock.ManageStockState;

/**
 * The View Model for the Manage Home View.
 */
public class ManageHomeViewModel extends ViewModel<ManageHomeState> {
    public final static String TITLE = "Buy/Sell Home";

    public final static String BUY_DESCRIPTION = "Would you like to buy a home?";
    public final static String SELL_DESCRIPTION = "Would you like to sell ur home";

    public final static String BACK_LABEL = "Back";
    public final static String BUY_LABEL = "Buy";
    public final static String SELL_LABEL = "Sell";

    public final static String[] HOME_DESCRIPTIONS = {
        "Luxurious 3 bed/3 bath house with a pool for 500k.",
        "Small 2 bed/1 bath apartment for 300k.",
    };

    public final static double[] HOME_PRICES = {500_000, 300_000};

    public ManageHomeViewModel() {
        super("manageHome");
        setState(new ManageHomeState());
    }
}
