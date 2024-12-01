package use_case.game_decision;

import entity.Assets;
import entity.Avatar;
import entity.Decision;
import entity.Question;

/**
 * Input Data for the Game Decision use case.
 */
public class GameDecisionInputData {
    private final String username;
    private final int age;
    private final String name;
    private final boolean darkMode;
    private final Question question;
    private final Decision decision;
    public Assets assets;
    private Avatar avatar;
    private int happiness;
    private double salary;

    public GameDecisionInputData(String username, int age, String name, boolean darkMode, Question question, Assets assets, Avatar avatar, Decision decision, int happiness, double salary) {
        this.username = username;
        this.age = age;
        this.name = name;
        this.darkMode = darkMode;
        this.question = question;
        this.assets = assets;
        this.avatar = avatar;
        this.decision = decision;
        this.happiness = happiness;
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public Question getQuestion() {
        return question;
    }

    public Decision getDecisionQuestion() {
        return decision;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return name;
    }

    public Assets getAssets() {
        return assets;
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
}
