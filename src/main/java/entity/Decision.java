package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Decision {
    private LocalDateTime timestamp;
    private final String decisionText;
    private final double happinessChange;
    private final double salaryChange;
    private final double netWorthChange;

    public Decision(LocalDateTime timestamp, String decisionText, double netWorthChange, double happinessChange,
                    double salaryChange) {
        this.timestamp = timestamp;
        this.decisionText = decisionText;
        this.happinessChange = happinessChange;
        this.salaryChange = salaryChange;
        this.netWorthChange = netWorthChange;
    }

    public static Decision fromJson(Map<String, Object> json) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime timestamp = LocalDateTime.parse((String) json.get("timestamp"), formatter);
        String decisionText = (String) json.get("decisionText");
        double netWorthChange = ((Number) json.get("netWorthChange")).doubleValue();
        double happinessChange = ((Number) json.get("happinessChange")).doubleValue();
        double salaryChange = ((Number) json.get("salaryChange")).doubleValue();

        return new Decision(timestamp, decisionText, netWorthChange, happinessChange, salaryChange);
    }


    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDecisionText() { return decisionText; }

    public double getHappinessChange() {
        return happinessChange;
    }

    public double getSalaryChange() {
        return salaryChange;
    }

    public double getNetWorthChange() { return netWorthChange;
    }
}