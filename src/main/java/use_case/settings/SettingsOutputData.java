package use_case.settings;

/**
 * Output Data for the Settings Use Case.
 */
public class SettingsOutputData {
    private final String username;

    public SettingsOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
