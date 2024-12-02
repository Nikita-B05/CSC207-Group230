package use_case.manage_home;

import entity.User;

/**
 * DAO for the Manage Home Use Case.
 */
public interface ManageHomeDataAccessInterface {

    /**
     * Returns the current user.
     * @return the current user.
     */
    User getCurrentUser();

    /**
     * Updates the user's cash.
     * @param newCash user's new cash.
     */
    void updateUserCash(double newCash);

    /**
     * Updates the user's home.
     * @param newHome user's new home.
     */
    void updateUserHome(double newHome);
}
