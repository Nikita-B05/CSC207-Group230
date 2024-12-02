package interface_adapter.decision_log;

import entity.Decision;
import java.util.List;
import java.util.ArrayList;

/**
 * The state for the Decision Log View.
 */
public class DecisionLogState {

    private String username;
    private int age;
    private double home;
    private List<Decision> decisions;
    private double totalNetWorthChange;
    private int totalHappinessChange;
    private boolean isDarkMode;

    // Constructor
    public DecisionLogState() {
        this.username = "";
        this.decisions = new ArrayList<>();
        this.home = 0;
        this.totalNetWorthChange = 0;
        this.totalHappinessChange = 0;
        this.isDarkMode = false;
    }

    // Getters

    public void setUsername(String username) { this.username = username; }

    public String getUsername() {
        return username;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public double getTotalNetWorthChange() {
        return totalNetWorthChange;
    }

    public int getTotalHappinessChange() {
        return totalHappinessChange;
    }

    public Object getAge() { return age; }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    // setters

    public void setHome(double home) {
        this.home = home;
    }

    public void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

    public void setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
    }

    public void setDarkModeEnabled(boolean darkMode) {
        isDarkMode = darkMode;
    }
}

