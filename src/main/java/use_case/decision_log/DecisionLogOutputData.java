package use_case.decision_log;

import entity.Decision;
import java.util.List;

/**
 * The Output Data for the decision log Use Case.
 */
public class DecisionLogOutputData {

    private final List<Decision> decisions;

    public DecisionLogOutputData(List<Decision> decisions) {
        this.decisions = decisions;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }
}
