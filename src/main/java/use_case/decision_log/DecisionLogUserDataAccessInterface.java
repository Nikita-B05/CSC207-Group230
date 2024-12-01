package use_case.decision_log;

import entity.Decision;
import entity.User;

import java.util.List;

//DAO for the Decision Log Use Case. okay no clue what's going on here do it later

public interface DecisionLogUserDataAccessInterface {
    /**
     * Loads the decision log.
     * @param username to look up the user.
     * @return The decision log.
     */
    List<Decision> getDecisions(String username);

    User get(String username);
}