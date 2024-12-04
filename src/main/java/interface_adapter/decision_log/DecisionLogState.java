package interface_adapter.decision_log;

import entity.Decision;

import java.util.List;
import java.util.ArrayList;

/**
 * The state for the Decision Log View.
 */
public class DecisionLogState {

    private String username;
    private List<Decision> decisions;
    private double totalNetWorthChange;
    private int totalHappinessChange;
    private boolean isDarkMode;

    // Constructor
    public DecisionLogState() {
        this.username = "";
        this.decisions = new ArrayList<>();
        this.totalNetWorthChange = 0;
        this.totalHappinessChange = 0;
        this.isDarkMode = false;
    }

    // Getters

    public String getUsername() {
        return username;
    }

    public List<Decision> getDecisions() {
        return new ArrayList<>(decisions);
    }

    public double getTotalNetWorthChange() {
        calculateTotals();
        return totalNetWorthChange;
    }

    public int getTotalHappinessChange() {
        calculateTotals();
        return totalHappinessChange;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    // Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = new ArrayList<>(decisions);
        calculateTotals();
    }

    public void setDarkModeEnabled(boolean darkMode) {
        isDarkMode = darkMode;
    }

    // Helper method to calculate totals directly from the decisions list
    private void calculateTotals() {
        this.totalNetWorthChange = 0;
        this.totalHappinessChange = 0;
        
        if (decisions != null) {
            for (Decision decision : decisions) {
                this.totalNetWorthChange += decision.getNetWorthChange();
                this.totalHappinessChange += decision.getHappinessChange();
            }
        }
    }

    public void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

}
