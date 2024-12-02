package use_case.choose_asset;

import entity.Assets;
import entity.Avatar;
import entity.Question;

import java.util.HashMap;
import java.util.Map;

public class ChooseAssetOutputData {

    private final String username;
    private final boolean isDarkMode;
    private double cash;
    private double home;
    private String[] stockNames;
    private Map<String, String> nameToCode;
    private Map<String, Double> codeToPrice;

    private Assets assets;
    private int age;
    private String characterName;
    private Avatar avatar;
    private Question question;
    private Map<String, Double> stockPrices;
    private double salary;
    private int happiness;

    public ChooseAssetOutputData(String username) {
        this.username = username;
        isDarkMode = false;
        cash = 0.0;
        home = 0.0;
        stockNames = null;
        nameToCode = null;
        codeToPrice = null;
    }

    public ChooseAssetOutputData(String username, boolean isDarkMode, double cash, double home) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.cash = cash;
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

    public ChooseAssetOutputData(
            String username,
            boolean isDarkMode,
            Assets assets,
            int age,
            String characterName,
            Avatar avatar,
            Question question,
            Map<String, Double> stockPrices,
            double salary,
            int happiness
    ) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.assets = assets;
        this.age = age;
        this.characterName = characterName;
        this.avatar = avatar;
        this.question = question;
        this.stockPrices = stockPrices;
        this.salary = salary;
        this.happiness = happiness;
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

    public Assets getAssets() {
        return assets;
    }

    public int getAge() {
        return age;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Question getQuestion() {
        return question;
    }

    public Map<String, Double> getStockPrices() {
        return stockPrices;
    }

    public double getSalary() {
        return salary;
    }

    public int getHappiness() {
        return happiness;
    }
}
