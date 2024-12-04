package interface_adapter.manage_home;

import interface_adapter.ViewModel;

/**
 * The View Model for the Manage Home View.
 */
public class ManageHomeViewModel extends ViewModel<ManageHomeState> {
    public static final String TITLE = "Buy/Sell Home";

    public static final String BUY_DESCRIPTION = "Would you like to buy a home?";
    public static final String SELL_DESCRIPTION = "Would you like to sell ur home";

    public static final String BACK_LABEL = "Back";
    public static final String BUY_LABEL = "Buy";
    public static final String SELL_LABEL = "Sell";

    public static final String[] HOME_DESCRIPTIONS = {
        "Luxurious 3 bed/3 bath house with a pool for 500k.",
        "Small 2 bed/1 bath apartment for 300k.",
    };

    public static final double[] HOME_PRICES = {500_000, 300_000};

    public ManageHomeViewModel() {
        super("manageHome");
        setState(new ManageHomeState());
    }
}
