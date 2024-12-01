package interface_adapter.game_decision;

import entity.*;

/**
 * State for the Game Decision View.
 */
public class GameDecisionState {

    private String username;
    private String characterName;
    private int age;
    private Question question;
    private boolean darkModeEnabled;
    private Decision decisionPicked;
    private Assets assets;
    private String decisionError;
    private Avatar avatar;


    public String getDecisionError() {
        return decisionError;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Decision getDecisionPicked() {
        return decisionPicked;
    }

    public void setDecisionPicked(Decision decisionPicked) {
        this.decisionPicked = decisionPicked;
    }

    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public void setDarkModeEnabled(boolean darkModeEnabled) {
        this.darkModeEnabled = darkModeEnabled;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDecisionError(String errorMessage) {
        this.decisionError = errorMessage;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {this.avatar = avatar;}
}
