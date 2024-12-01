package use_case.homepage;

import entity.Avatar;
import entity.Decision;
import entity.Question;

import java.util.ArrayList;

public class HomepageOutputData {
    private final String username;
    private final String characterName;
    private final Avatar avatar;
    private final boolean isDarkMode;
    private final ArrayList<Decision> decisions;
    private int age;
    private Question question;

    public HomepageOutputData(String username, String characterName, Avatar avatar, boolean isDarkMode, ArrayList<Decision> decisions, int age, Question question) {
        this.username = username;
        this.characterName = characterName;
        this.avatar = avatar;
        this.isDarkMode = isDarkMode;
        this.decisions = decisions;
        this.age = age;
        this.question = question;
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
}
