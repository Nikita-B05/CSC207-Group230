package interface_adapter.settings;

/**
 * State for the Settings View.
 */
public class SettingsState {
    private String username;
    private boolean darkModeEnabled;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public void setDarkModeEnabled(boolean darkModeEnabled) {
        this.darkModeEnabled = darkModeEnabled;
    }
}
