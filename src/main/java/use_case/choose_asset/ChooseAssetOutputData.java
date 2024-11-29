package use_case.choose_asset;

public class ChooseAssetOutputData {

    private final String username;
    private final boolean isDarkMode;
    private final double home;
    private final String[] stockNames;

    public ChooseAssetOutputData(String username, boolean isDarkMode, double home, String[] stockNames) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.home = home;
        this.stockNames = stockNames;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public double getHome() {
        return home;
    }

    public String[] getStockNames() {
        return stockNames;
    }
}
