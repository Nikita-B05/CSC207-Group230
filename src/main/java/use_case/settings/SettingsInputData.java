package use_case.settings;

/**
 * The Input Data for the Settings Use Case.
 */
public class SettingsInputData {
    private final String username;

    public SettingsInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
