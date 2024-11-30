package entity;

import org.bson.io.BsonOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Assets {
    private final double home;
    private final ArrayList<Stock> stocks;
    private double cash;
    private final double car;

    public Assets() {
        home = 0;
        stocks = new ArrayList<>();
        cash = 0;
        car = 0;
    }

    public Assets(double home, ArrayList<Stock> stocks, double cash, double car) {
        this.home = home;
        this.stocks = stocks;
        this.cash = cash;
        this.car = car;
    }

    public double getHome() {
        return home;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public double getCash() {
        return cash;
    }

    public double getCar() {
        return car;
    }

    public void buyStock(String stockCode, int quantity, double buyPrice) {
        stocks.add(new Stock(stockCode, quantity, buyPrice, 365));
        cash -= quantity * buyPrice;
    }

    public boolean canBuyStock(String stockCode, int quantity, Map<String, Double> stockPrices) {
        return cash >= stockPrices.get(stockCode) * quantity;
    }

    public void sellStock(String stockCode, int quantity, double sellPrice) {
        double total = 0;
        int i = 0;
        while (i < stocks.size()) {
            Stock stock = stocks.get(i);
            if (!stock.getStockCode().equals(stockCode)) {
                i++;
            }
            else if (stock.getQuantity() == quantity) {
                stocks.remove(i);
                total += quantity * getTransformedSellPrice(stock.getBuyPrice(), sellPrice, stock.getMultiplier());
                break;
            }
            else if (stock.getQuantity() > quantity) {
                stock.setQuantity(stock.getQuantity() - quantity);
                total += quantity * getTransformedSellPrice(stock.getBuyPrice(), sellPrice, stock.getMultiplier());
                break;
            }
            else {
                stocks.remove(i);
                quantity -= stock.getQuantity();
                total += stock.getQuantity()
                        * getTransformedSellPrice(stock.getBuyPrice(), sellPrice, stock.getMultiplier());
            }
        }
        cash += total;
    }

    private double getTransformedSellPrice(double buyPrice, double sellPrice, int multiplier) {
        return buyPrice + multiplier * (sellPrice - buyPrice);
    }

    public boolean isValidSell(String stockCode, int quantity) {
        int totalQuantity = 0;
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(stockCode)) {
                totalQuantity += stock.getQuantity();
            }
        }
        return totalQuantity >= quantity;
    }

    public double getTotal(HashMap<String, Double> stockPrices) {
        double total = 0;
        total += home;
        for (Stock stock : stocks) {
            total += stock.getTotal(stockPrices.get(stock.getStockCode()));
        }
        total += cash;
        total += car;
        return total;
    }
}
