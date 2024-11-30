package use_case.choose_asset;

import java.util.HashMap;
import java.util.Map;

public class ChooseAssetOutputData {

    private final String username;
    private final boolean isDarkMode;
    private final double cash;
    private final double home;
    private final String[] stockNames;
    private final Map<String, String> nameToCode;
    private final Map<String, Double> codeToPrice;

    public ChooseAssetOutputData(String username, boolean isDarkMode, double home) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.cash = 0.0;
        this.home = home;
        this.stockNames = new String[0];
        this.nameToCode = new HashMap<>();
        this.codeToPrice = new HashMap<>();
    }

    public ChooseAssetOutputData(
            String username,
            boolean isDarkMode,
            double cash,
            String[] stockNames,
            Map<String, String> nameToCode,
            Map<String, Double> codeToPrice
    ) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.cash = cash;
        this.home = 0.0;
        this.stockNames = stockNames;
        this.nameToCode = nameToCode;
        this.codeToPrice = codeToPrice;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public double getCash() {
        return cash;
    }

    public double getHome() {
        return home;
    }

    public String[] getStockNames() {
        return stockNames;
    }

    public Map<String, String> getNameToCode() {
        return nameToCode;
    }

    public Map<String, Double> getCodeToPrice() {
        return codeToPrice;
    }
}
