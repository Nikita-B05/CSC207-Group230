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
        outputBoundary.prepareChangePasswordView();
    }

    @Override
    public void logout(SettingsInputData inputData) {
        userDataAccess.setCurrentUsername(null);
        SettingsOutputData outputData = new SettingsOutputData(inputData.getUsername(), true);
        outputBoundary.prepareLogoutView(outputData);
    }

    @Override
    public void toggleDarkMode(SettingsInputData inputData) {
        boolean darkModeEnabled = inputData.isDarkMode();
        userDataAccess.setDarkMode(darkModeEnabled);
        outputBoundary.updateDarkMode(darkModeEnabled);
    }
}
