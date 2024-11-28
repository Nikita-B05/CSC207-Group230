package entity;

public class Stock {
    private String stockCode;
    private int quantity;
    private double buyPrice;
    private int multiplier;

    public Stock(String stockCode, int quantity, double buyPrice, int multiplier) {
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.multiplier = multiplier;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public double getTotal(double currentPrice) {
        return quantity * (currentPrice - buyPrice) * multiplier;
    }
}
