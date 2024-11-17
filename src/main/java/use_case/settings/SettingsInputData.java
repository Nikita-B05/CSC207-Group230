package use_case.settings;

/**
 * Input Data for the Settings use case.
 */
public class SettingsInputData {
    private final String username;
    private final boolean darkMode;

    public SettingsInputData(String username) {
        this.username = username;
        this.darkMode = false;
    }

    public SettingsInputData(boolean darkMode) {
        this.username = null;
        this.darkMode = darkMode;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDarkMode() {
        return darkMode;
    }
}
