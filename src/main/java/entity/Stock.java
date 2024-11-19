package entity;

public class Stock {
    private final int stockCode;
    private final int quantity;
    private final int buyPrice;
    private int sellPrice;
    private int multiplier;

    public Stock(int stockCode, int quantity, int buyPrice, int sellPrice, int multiplier) {
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.multiplier = multiplier;
    }

    public int getStockCode() {
        return stockCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getTotal() {
        return quantity * (sellPrice - buyPrice) * multiplier;
    }
}
