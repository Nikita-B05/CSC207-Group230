package entity;

import java.util.ArrayList;

public class Assets {
    private final int home;
    private final ArrayList<Stock> stocks;
    private final int cash;
    private final int car;

    public Assets() {
        home = 0;
        stocks = new ArrayList<Stock>();
        cash = 0;
        car = 0;
    }

    public Assets(int home, ArrayList<Stock> stocks, int cash, int car) {
        this.home = home;
        this.stocks = stocks;
        this.cash = cash;
        this.car = car;
    }

    public int getHome() {
        return home;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public int getCash() {
        return cash;
    }

    public int getCar() {
        return car;
    }

    public int getTotal() {
        int total = 0;
        total += home;
        for (Stock stock : stocks) {
            total += stock.getTotal();
        }
        total += cash;
        total += car;
        return total;
    }
}
