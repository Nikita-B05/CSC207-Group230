package use_case.settings;

import entity.User;

/**
 * DAO for the Settings Use Case.
 */
public interface SettingsUserDataAccessInterface {
    String getCurrentUsername();
    void setCurrentUsername(String username);
    void updateUserDarkMode(boolean darkMode);
    User getCurrentUser();
}
