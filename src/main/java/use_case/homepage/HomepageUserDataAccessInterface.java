package use_case.homepage;

import entity.User;

/**
 * DAO for the Homepage Use Case.
 */
public interface HomepageUserDataAccessInterface {
    /**
     * Returns the current user.
     * @return the current user.
     */
    User getCurrentUser();
}