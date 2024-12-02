package use_case.decision_log;

import entity.Decision;
import entity.User;

import java.util.List;

//DAO for the Decision Log Use Case.

public interface DecisionLogUserDataAccessInterface {

    /**
     * Returns the current user.
     * @return the current user.
     */
    User getCurrentUser();
}