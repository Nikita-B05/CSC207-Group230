package interface_adapter.decision_log;

import entity.Decision;

import java.util.List;

/**
 * The state for the Decision Log View.
 */
public class DecisionLogState {
    private String username;
    private List<Decision> decisions;
    private boolean isDarkMode = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }

    public Object getTotalNetWorthChange() {
        return decisions.stream().mapToDouble(Decision::getNetWorthChange).sum();
    }

    public Object getTotalHappinessChange() {
        return decisions.stream().mapToDouble(Decision::getHappinessChange).sum();
    }

    public boolean isDarkModeEnabled() { return false; }

    public boolean setDarkModeEnabled(boolean darkMode) { return darkMode; }
}

