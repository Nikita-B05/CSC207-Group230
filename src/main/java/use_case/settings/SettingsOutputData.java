package use_case.settings;

/**
 * Output Data for the Settings Use Case.
 */
public class SettingsOutputData {
    private final String username;
    private boolean isDarkMode;

    public SettingsOutputData(String username, boolean isDarkMode) {
        this.username = username;
        this.isDarkMode = isDarkMode;
    }

    public String getUsername() {
        return username;
    }

    public boolean getDarkMode() {
        return isDarkMode;
    }
}
