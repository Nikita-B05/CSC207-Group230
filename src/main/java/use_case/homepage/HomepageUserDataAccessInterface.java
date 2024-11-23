package use_case.homepage;

import entity.User;

/**
 * DAO for the Homepage Use Case.
 */
public interface HomepageUserDataAccessInterface {
    // TODO
    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);
}