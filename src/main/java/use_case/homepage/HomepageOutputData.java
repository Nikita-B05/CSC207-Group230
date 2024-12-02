package use_case.homepage;

import entity.Assets;
import entity.Avatar;
import entity.Decision;
import entity.Question;

import java.util.ArrayList;
import java.util.Map;

public class HomepageOutputData {
    private final String username;
    private final String characterName;
    private final Avatar avatar;
    private final boolean isDarkMode;
    private final ArrayList<Decision> decisions;
    private int age;
    private Question question;
    int happiness;
    double salary;
    private Map<String, Double> stockPrices;
    private Assets assets;

    public HomepageOutputData(String username, String characterName, Avatar avatar, boolean isDarkMode, ArrayList<Decision> decisions, int age, Question question, int happiness, double salary, Assets assets) {
        this.username = username;
        this.characterName = characterName;
        this.avatar = avatar;
        this.isDarkMode = isDarkMode;
        this.decisions = decisions;
        this.age = age;
        this.question = question;
        this.happiness = happiness;
        this.salary = salary;
        this.assets = assets;
    }

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public ArrayList<Decision> getDecisions() {
        return decisions;
    }

    public int getAge() {
        return age;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Question getQuestion(int age) {
        return question;
    }

    public int getHappiness() {
        return happiness;
    }

    public double getSalary() {
        return salary;
    }

    public Map<String, Double> getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(Map<String, Double> stockPrices) {
        this.stockPrices = stockPrices;
    }

    public Assets getAssets() {
        return this.assets;
    }
}
