package use_case.decision_log;

import entity.Avatar;
import entity.Decision;
import entity.Question;

import java.util.List;

/**
 * The Output Data for the decision log Use Case.
 */
public class DecisionLogOutputData {
    private final String username;
    private final boolean isDarkMode;

    private int age;
    private Question question;
    private int happiness;
    private List<Decision> decisions;
    private Avatar avatar;

    public DecisionLogOutputData(
            String username,
            List<Decision> decisions,
            boolean isDarkMode) {
        this.username = username;
        this.decisions = decisions;
        this.isDarkMode = isDarkMode;
    }

    public int getAge() {
        return age;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public Object getHome() {
        return null;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getName() {
        return null;
    }
}
