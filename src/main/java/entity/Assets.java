package entity;

import java.util.ArrayList;
import java.util.Map;

/**
 * The Assets class represents the assets owned by a user, including home, stocks, cash, and car.
 * It provides methods for buying and selling stocks, as well as managing and calculating the total value of assets.
 */
public class Assets {
    public static final int DAY_FACTOR = 365;
    private final double home;
    private final ArrayList<Stock> stocks;
    private double cash;
    private final double car;

    /**
     * Default constructor that initializes assets with zero values.
     */
    public Assets() {
        home = 0;
        stocks = new ArrayList<>();
        cash = 0;
        car = 0;
    }

    /**
     * Constructs an Assets object with specified values for home, stocks, cash, and car.
     *
     * @param home The value of the home asset.
     * @param stocks The list of stocks owned by the user.
     * @param cash The amount of cash the user has.
     * @param car The value of the car asset.
     */
    public Assets(double home, ArrayList<Stock> stocks, double cash, double car) {
        this.home = home;
        this.stocks = stocks;
        this.cash = cash;
        this.car = car;
    }

    /**
     * Gets the value of the home asset.
     *
     * @return The value of the home.
     */
    public double getHome() {
        return home;
    }

    /**
     * Gets the list of stocks owned by the user.
     *
     * @return The list of stocks.
     */
    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    /**
     * Gets the amount of cash the user has.
     *
     * @return The amount of cash.
     */
    public double getCash() {
        return cash;
    }

    /**
     * Gets the value of the car asset.
     *
     * @return The value of the car.
     */
    public double getCar() {
        return car;
    }

    /**
     * Buys a stock with a given stock code, quantity, and buy price, reducing the user's cash.
     *
     * @param stockCode The stock code of the stock to buy.
     * @param quantity The number of shares to buy.
     * @param buyPrice The price at which to buy the stock.
     */
    public void buyStock(String stockCode, int quantity, double buyPrice) {
        stocks.add(new Stock(stockCode, quantity, buyPrice, DAY_FACTOR));
        cash -= quantity * buyPrice;
    }

    /**
     * Checks if the user can afford to buy a given quantity of stock based on the available cash.
     *
     * @param stockCode The stock code of the stock to check.
     * @param quantity The number of shares to buy.
     * @param stockPrices The current stock prices.
     * @return True if the user can afford the stock, false otherwise.
     */
    public boolean canBuyStock(String stockCode, int quantity, Map<String, Double> stockPrices) {
        return cash >= stockPrices.get(stockCode) * quantity;
    }

    /**
     * Sells a stock with a given stock code, quantity, and sell price, increasing the user's cash.
     *
     * @param stockCode The stock code of the stock to sell.
     * @param quantity The number of shares to sell.
     * @param sellPrice The price at which to sell the stock.
     */
    public void sellStock(String stockCode, int quantity, double sellPrice) {
        double total = 0;
        int i = 0;
        while (i < stocks.size()) {
            final Stock stock = stocks.get(i);
            if (!stock.getStockCode().equals(stockCode)) {
                i++;
            }
            else if (stock.getQuantity() == quantity) {
                stocks.remove(i);
                total += quantity * sellPrice;
                break;
            }
            else if (stock.getQuantity() > quantity) {
                stock.setQuantity(stock.getQuantity() - quantity);
                total += quantity * sellPrice;
                break;
            }
            else {
                stocks.remove(i);
                quantity -= stock.getQuantity();
                total += stock.getQuantity() * sellPrice;
            }
        }
        cash += total;
    }

    /**
     * Checks if the user has enough quantity of a specific stock to sell.
     *
     * @param stockCode The stock code of the stock to check.
     * @param quantity The number of shares to check.
     * @return True if the user has enough shares to sell, false otherwise.
     */
    public boolean isValidSell(String stockCode, int quantity) {
        int totalQuantity = 0;
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(stockCode)) {
                totalQuantity += stock.getQuantity();
            }
        }
        return totalQuantity >= quantity;
    }

    /**
     * Calculates the total value of the user's assets, including home, stocks, cash, and car.
     *
     * @param stockPrices The current stock prices used to calculate the total value of stocks.
     * @return The total value of all assets.
     */
    public double getTotal(Map<String, Double> stockPrices) {
        double total = 0;
        total += home;
        for (Stock stock : stocks) {
            total += stock.getTotal(stockPrices.get(stock.getStockCode()));
        }
        total += cash;
        total += car;
        return total;
    }

    /**
     * Changes the amount of cash the user has by a specified amount (can be positive or negative).
     *
     * @param cashChange The amount to add or subtract from the user's cash.
     */
    public void changeCash(double cashChange) {
        this.cash += cashChange;
    }
}
