package use_case.game_decision;

import entity.Decision;
import entity.Question;

/**
 * Input Data for the Game Decision use case.
 */
public class GameDecisionInputData {
    private final String username;
    private final boolean darkMode;
    private final Question question;

    public GameDecisionInputData(String username, boolean darkMode, Question question) {
        this.username = username;
        this.darkMode = darkMode;
        this.question = question;
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
        return question.getAge();
    }
}
