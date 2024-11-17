package use_case.settings;

/**
 * Output Data for the Settings Use Case.
 */
public class SettingsOutputData {
    private final String username;
    private final boolean logoutSuccessful;

    public SettingsOutputData(String username, boolean logoutSuccessful) {
        this.username = username;
        this.logoutSuccessful = logoutSuccessful;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLogoutSuccessful() {
        return logoutSuccessful;
    }
}
