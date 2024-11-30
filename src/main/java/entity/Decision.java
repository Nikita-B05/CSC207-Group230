package entity;

import java.time.LocalDateTime;

public class Decision {
    private LocalDateTime timestamp;
    private final String decisionText;
    private final double cashChange;
    private final double happinessChange;

    public Decision(LocalDateTime timestamp, String decisionText, double cashChange, double happinessChange) {
        this.timestamp = timestamp;
        this.decisionText = decisionText;
        this.cashChange = cashChange;
        this.happinessChange = happinessChange;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDecisionText() { return decisionText; }

    public double getCashChange() {
        return cashChange;
    }

    public double getHappinessChange() {
        return happinessChange;
    }
}