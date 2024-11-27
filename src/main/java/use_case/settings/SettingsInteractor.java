package use_case.settings;

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
        SettingsOutputData settingsOutputData = new SettingsOutputData(inputData.getUsername(), inputData.isDarkMode());
        outputBoundary.prepareChangePasswordView(settingsOutputData);
    }

    @Override
    public void logout(SettingsInputData inputData) {
        userDataAccess.setCurrentUsername(null);
        SettingsOutputData outputData = new SettingsOutputData(inputData.getUsername(), inputData.isDarkMode());
        outputBoundary.prepareLogoutView(outputData);
    }
}
