package use_case.input_name;

import entity.Avatar;
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

    User getCurrentUser();

    void updateCharacterName(String characterName);

    void updateAvatar(Avatar avatar);
}