package use_case.game_decision;

import entity.*;

import java.util.Map;

/**
 * Output Data for the Game Decision Use Case.
 */
public class GameDecisionOutputData {
    private final String username;
    private final Map<String, Double> stockPrices;
    private boolean isDarkMode;
    private final String name;
    private final Assets assets;
    private final Avatar avatar;
    private final int age;
    private final int happiness;
    private final double salary;


    public GameDecisionOutputData(String username, boolean isDarkMode, String name, Assets assets, Avatar avatar, int age, int happiness, double salary, Map<String, Double> stockPrices) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.name = name;
        this.assets = assets;
        this.avatar = avatar;
        this.age = age;
        this.happiness = happiness;
        this.salary = salary;
        this.stockPrices = stockPrices;
    }

    public GameDecisionOutputData(String username, boolean isDarkMode, String name, Assets assets, Avatar avatar, int age, int happiness, double salary) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.name = name;
        this.assets = assets;
        this.avatar = avatar;
        this.age = age;
        this.happiness = happiness;
        this.salary = salary;
        this.stockPrices = null;
    }

    public int getAge() {
        return age;
    }

    public Assets getAssets() {
        return assets;
    }

    public String getName() {
        return name;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public double getSalary() {
        return salary;
    }

    public int getHappiness() {
        return happiness;
    }

    public double getNetWorth() {
        return assets.getTotal(this.stockPrices);
    }
}
