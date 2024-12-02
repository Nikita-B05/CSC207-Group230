package interface_adapter.manage_stock;

import java.util.Map;

public class ManageStockState {
    private String[] stockNames = new String[0];
    private Map<String, Double> codeToPrice;
    private Map<String, String> nameToCode;
    private boolean isDarkMode;
    private double cash;
    private String quantity;
    private String code;
    private String errorMessage;
    private String successMessage;

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

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Map<String, Double> getCodeToPrice() {
        return codeToPrice;
    }

    public void setCodeToPrice(Map<String, Double> codeToPrice) {
        this.codeToPrice = codeToPrice;
    }

    public Map<String, String> getNameToCode() {
        return nameToCode;
    }

    public void setNameToCode(Map<String, String> nameToCode) {
        this.nameToCode = nameToCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}

