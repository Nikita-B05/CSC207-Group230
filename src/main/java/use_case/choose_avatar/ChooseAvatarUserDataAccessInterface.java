package use_case.choose_avatar;

import entity.User;

/**
 * Data Access Interface for the Choose Avatar Use Case.
 */
public interface ChooseAvatarUserDataAccessInterface {
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