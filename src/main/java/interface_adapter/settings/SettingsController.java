package interface_adapter.settings;

import use_case.settings.SettingsInputBoundary;
import use_case.settings.SettingsInputData;

/**
 * Controller for the Settings use case.
 */
public class SettingsController {
    private final SettingsInputBoundary settingsInteractor;

    public SettingsController(SettingsInputBoundary settingsInteractor) {
        this.settingsInteractor = settingsInteractor;
    }

    public void changePassword(String username) {
        SettingsInputData inputData = new SettingsInputData(username);
        settingsInteractor.navigateToChangePassword(inputData);
    }

    public void logout(String username) {
        SettingsInputData inputData = new SettingsInputData(username);
        settingsInteractor.logout(inputData);
    }

}
