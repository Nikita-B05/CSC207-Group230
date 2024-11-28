package use_case.settings;

/**
 * Input Boundary for the Settings use case.
 */
public interface SettingsInputBoundary {
    void navigateToChangePassword(SettingsInputData inputData);
    void logout(SettingsInputData inputData);
    void navigateToHomePage(SettingsInputData inputData);
}
