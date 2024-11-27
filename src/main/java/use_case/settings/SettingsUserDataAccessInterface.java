package use_case.settings;

/**
 * DAO for the Settings Use Case.
 */
public interface SettingsUserDataAccessInterface {
    String getCurrentUsername();
    void setCurrentUsername(String username);
    void updateUserDarkMode(boolean darkMode);
}
