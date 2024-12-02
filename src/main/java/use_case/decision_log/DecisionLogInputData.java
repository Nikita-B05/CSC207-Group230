package use_case.decision_log;

import entity.Decision;

import java.util.ArrayList;
import java.util.List;

/**
 * The Input Data for the decision log Use Case.
 */
public class DecisionLogInputData {

    private final String username;

    public DecisionLogInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<Decision> getDecisions() { return new ArrayList<>(); }
}
