package use_case.settings;

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
}
