package use_case.settings;

/**
 * The Output Boundary for the Settings Use Case.
 */
public interface SettingsOutputBoundary {
    void prepareChangePasswordView();
    void prepareLogoutView(SettingsOutputData outputData);
}
