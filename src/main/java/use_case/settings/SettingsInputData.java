package use_case.settings;

/**
 * Input Data for the Settings use case.
 */
public class SettingsInputData {
    private final String username;
    private final boolean darkMode;

    public SettingsInputData(String username, boolean darkMode) {
        this.username = username;
        this.darkMode = darkMode;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDarkMode() {
        return darkMode;
    }
}
