package use_case.decision_log;

import entity.Decision;
import java.util.List;

/**
 * The Output Data for the decision log Use Case.
 */
public class DecisionLogOutputData {
    private final List<Decision> decisions;
    private final String username;
    private boolean isDarkMode;

    public DecisionLogOutputData(List<Decision> decisions, String username) {
        this.decisions = decisions;
        this.username = username;
        this.isDarkMode = isDarkMode;
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

    public void setDarkMode(boolean darkMode) {
        this.isDarkMode = darkMode;
    }
}
