package entity;

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
        String decisionText = (json.get("decisionText") != null) ? (String) json.get("decisionText") : "No decision";
        String response = (String) json.get("response");
        double netWorthChange = ((Number) json.get("netWorthChange")).doubleValue();
        int happinessChange = ((Number) json.get("happinessChange")).intValue();
        double salaryChange = ((Number) json.get("salaryChange")).doubleValue();

        return new Decision(age, decisionText, response, happinessChange, happinessChange, salaryChange);
    }


    public int getAge() { return age; }
    public String getDecisionText() { return decisionText; }
    public String getResponse() { return response; }
    public double getNetWorthChange() { return netWorthChange; }
    public int getHappinessChange() { return happinessChange; }
    public double getSalaryChange() { return salaryChange; }
}
