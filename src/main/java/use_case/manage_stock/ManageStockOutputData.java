package use_case.manage_stock;

/**
 * The Output Data for the Manage Stock Use Case.
 */
public class ManageStockOutputData {
    private final boolean isDarkMode;

    public ManageStockOutputData(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }
}
