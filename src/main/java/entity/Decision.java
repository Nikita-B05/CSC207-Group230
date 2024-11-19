package entity;

import java.time.LocalDateTime;

public class Decision {
    private final LocalDateTime timestamp;
    private final String decisionText;
    private final double netWorthChange;
    private final double happinessChange;

    public Decision(LocalDateTime timestamp, String decisionText, double netWorthChange, double happinessChange) {
        this.timestamp = timestamp;
        this.decisionText = decisionText;
        this.netWorthChange = netWorthChange;
        this.happinessChange = happinessChange;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDecisionText() {
        return decisionText;
    }

    public double getNetWorthChange() {
        return netWorthChange;
    }

    public double getHappinessChange() {
        return happinessChange;
    }
}