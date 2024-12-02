package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Decision {
    private LocalDateTime timestamp;
    private final String decisionText;
    private final double cashChange;
    private final double happinessChange;
    private final double salaryChange;
    private final double netWorthChange;
    private final Object decisionResponse;

    public Decision(LocalDateTime timestamp, String decisionText, double cashChange, double happinessChange,
                    double salaryChange, double netWorthChange, Object decisionResponse) {
        this.timestamp = timestamp;
        this.decisionText = decisionText;
        this.cashChange = cashChange;
        this.happinessChange = happinessChange;
        this.salaryChange = salaryChange;
        this.decisionResponse = decisionResponse;
        this.netWorthChange = netWorthChange;
    }

    public static Decision fromJson(Map<String, Object> json) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime timestamp = LocalDateTime.parse((String) json.get("timestamp"), formatter);
        String decisionText = (String) json.get("decisionText");
        double cashChange = ((Number) json.get("cashChange")).doubleValue();
        double happinessChange = ((Number) json.get("happinessChange")).doubleValue();
        double salaryChange = ((Number) json.get("salaryChange")).doubleValue();

        return new Decision(timestamp, decisionText, cashChange, happinessChange, salaryChange, 0, null);
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

    public double getSalaryChange() {
        return salaryChange;
    }

    public double getNetWorthChange() { return netWorthChange;
    }

    public Object getDecisionResponse() {  return decisionResponse;
    }
}