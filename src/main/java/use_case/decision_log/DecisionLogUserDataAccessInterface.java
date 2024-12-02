package use_case.decision_log;

import entity.Decision;
import entity.User;

import java.util.List;

//DAO for the Decision Log Use Case.

public interface DecisionLogUserDataAccessInterface {
    /**
     * Loads the decision log.
     * @param username to look up the user.
     * @return The decision log.
     */
    List<Decision> getDecisions(String username);

    /**
     * Returns the current user.
     * @return the current user.
     */
    User getCurrentUser();
}