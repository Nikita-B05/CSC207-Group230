package use_case.settings;

import entity.User;

/**
 * Interactor for the Settings use case.
 */
public class SettingsInteractor implements SettingsInputBoundary {
    private final SettingsUserDataAccessInterface userDataAccess;
    private final SettingsOutputBoundary outputBoundary;

    public SettingsInteractor(SettingsUserDataAccessInterface userDataAccess,
                              SettingsOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void navigateToChangePassword(SettingsInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        SettingsOutputData settingsOutputData = new SettingsOutputData(inputData.getUsername(), inputData.isDarkMode(), user.getAvatar(), user.getCharacterName());
        outputBoundary.prepareChangePasswordView(settingsOutputData);
    }



    @Override
    public void logout(SettingsInputData inputData) {
        userDataAccess.setCurrentUsername(null);
        User user = userDataAccess.getCurrentUser();
        SettingsOutputData outputData = new SettingsOutputData(inputData.getUsername(), inputData.isDarkMode(), user.getAvatar(), user.getCharacterName());
        outputBoundary.prepareLogoutView(outputData);
    }

    @Override
    public void navigateToHomePage(SettingsInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        SettingsOutputData outputData = new SettingsOutputData(inputData.getUsername(), inputData.isDarkMode(), user.getAvatar(), user.getCharacterName());
        outputBoundary.prepareHomepageView(outputData);
    }
}
