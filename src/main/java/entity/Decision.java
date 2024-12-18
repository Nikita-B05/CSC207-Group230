package entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decision {
    private int age;
    private final String decisionText;
    private final String response;
    private final double netWorthChange;
    private final int happinessChange;
    private final double salaryChange;

    // Regular constructor (without JSON handling)
    public Decision(int age, String decisionText, String response, double netWorthChange,
                    int happinessChange, double salaryChange) {
        this.age = age;
        this.decisionText = decisionText;
        this.response = response;
        this.netWorthChange = netWorthChange;
        this.happinessChange = happinessChange;
        this.salaryChange = salaryChange;
    }

    // fromJson method to assign values from a Map
    public static Decision fromJson(Map<String, Object> json) {
        int age = (int) json.get("age");
        String decisionText;
        if (json.get("decisionText") != null) {
            decisionText = (String) json.get("decisionText");
        }
        else {
            decisionText = "No decision";
        }
        String response = (String) json.get("response");
        double netWorthChange = (Integer) json.get("cashChange");
        int happinessChange = ((Number) json.get("happinessChange")).intValue();
        double salaryChange = ((Number) json.get("salaryChange")).doubleValue();

        return new Decision(age, decisionText, response, netWorthChange, happinessChange, salaryChange);
    }

    public static Map<String, Double> calculateTotalStats(List<Decision> decisions) {
        double totalNetWorthChange = 0;
        int totalHappinessChange = 0;

        for (Decision decision : decisions) {
            totalNetWorthChange += decision.getNetWorthChange();
            totalHappinessChange += decision.getHappinessChange();
        }

        Map<String, Double> totals = new HashMap<>();
        totals.put("totalNetWorth", totalNetWorthChange);
        totals.put("totalHappiness", (double) totalHappinessChange);

        return totals;
    }

    public int getAge() {
        return age;
    }

    public String getDecisionText() {
        return decisionText;
    }

    public String getResponse() {
        return response;
    }

    public double getNetWorthChange() {
        return netWorthChange;
    }

    public int getHappinessChange() {
        return happinessChange;
    }

    public double getSalaryChange() {
        return salaryChange;
    }
}
