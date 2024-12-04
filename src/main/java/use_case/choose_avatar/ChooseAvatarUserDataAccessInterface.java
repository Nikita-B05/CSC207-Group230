package use_case.choose_avatar;

import entity.Avatar;
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

    User getCurrentUser();

    void updateAvatar(Avatar avatar);
}
