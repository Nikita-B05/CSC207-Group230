package interface_adapter.manage_stock;

import entity.Stock;

public class ManageStockState {
    private String[] stockNames = new String[] {};
    private boolean isDarkMode;

    public String[] getStockNames() {
        return stockNames;
    }

    public void setStockNames(String[] stockNames) {
        this.stockNames = stockNames;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void setDarkMode(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
    }
}

