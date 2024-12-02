package use_case.manage_stock;

/**
 * The Input Data for the Manage Stock Use Case.
 */
public class ManageStockInputData {
    private final String stockCode;
    private final String quantity;
    private final boolean isBuying;

    public ManageStockInputData(String stockCode, String quantity, boolean isBuying) {
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.isBuying = isBuying;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public boolean isBuying() {
        return isBuying;
    }
}
