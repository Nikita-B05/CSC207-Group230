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
    public Assets assets;
    private Avatar avatar;

    public GameDecisionInputData(String username, int age, String name, boolean darkMode, Question question, Assets assets, Avatar avatar) {
        this.username = username;
        this.age = age;
        this.name = name;
        this.darkMode = darkMode;
        this.question = question;
        this.assets = assets;
        this.avatar = avatar;
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
        return question.getSelectedDecision();
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
}
