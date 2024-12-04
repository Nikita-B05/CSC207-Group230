package use_case.settings;

/**
 * Output Boundary for the Settings use case.
 */
public interface SettingsOutputBoundary {
    void prepareChangePasswordView(SettingsOutputData outputData);

    void prepareLogoutView(SettingsOutputData outputData);

    void prepareHomepageView(SettingsOutputData outputData);
}
