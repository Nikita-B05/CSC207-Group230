package interface_adapter.settings;

import use_case.settings.SettingsInputBoundary;
import use_case.settings.SettingsInputData;

/**
 * Controller for the Settings Use Case.
 */
public class SettingsController {
    private final SettingsInputBoundary settingsInteractor;

    public SettingsController(SettingsInputBoundary settingsInteractor) {
        this.settingsInteractor = settingsInteractor;
    }

    /**
     * Navigates to the Change Password view.
     */
    public void navigateToChangePassword(String username) {
        SettingsInputData inputData = new SettingsInputData(username);
        settingsInteractor.navigateToChangePassword(inputData);
    }

    /**
     * Logs out the current user.
     */
    public void logout(String username) {
        SettingsInputData inputData = new SettingsInputData(username);
        settingsInteractor.logout(inputData);
    }
}
