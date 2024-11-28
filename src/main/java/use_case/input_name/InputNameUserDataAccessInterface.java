package use_case.input_name;

import entity.User;

/**
 * Data Access Interface for the Input Name Use Case.
 */
public interface InputNameUserDataAccessInterface {
    /**
     * Retrieves a user by username.
     *
     * @param username the username to look up.
     * @return the User object.
     */
    User get(String username);

    /**
     * Saves the updated user data.
     *
     * @param user the User object to save.
     */
    void save(User user);
}