package interface_adapter.decision_log;

import entity.Decision;

import java.util.List;

/**
 * The state for the Decision Log View.
 */
public class DecisionLogState {
    private String username;
    private List<Decision> decisions;

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
}

