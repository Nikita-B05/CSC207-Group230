package use_case.settings;

/**
 * Output Boundary for the Settings use case.
 */
public interface SettingsOutputBoundary {
    void prepareChangePasswordView();
    void prepareLogoutView(SettingsOutputData outputData);
    void updateDarkMode(boolean darkModeEnabled);
}
