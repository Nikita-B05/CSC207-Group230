package use_case.decision_log;

import entity.Decision;
import entity.User;

import java.util.List;

//DAO for the Decision Log Use Case. okay no clue what's going on here do it later

public interface DecisionLogUserDataAccessInterface {
    /**
     * Loads the decision log.
     * @param user The user.
     * @return The decision log.
     */
    String loadDecisionLog(User user);

    List<Decision> getDecisions(String username);

    List<Decision> getCurrentUsername(String username);
}