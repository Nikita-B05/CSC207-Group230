package use_case.dark_mode;

import entity.User;

public class DarkModeInteractor implements DarkModeInputBoundary {
    private final DarkModeOutputBoundary outputBoundary;
    private final DarkModeUserDataAccessInterface userDataAccess;

    public DarkModeInteractor(DarkModeOutputBoundary outputBoundary,
                              DarkModeUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void toggleDarkMode(DarkModeInputData inputData) {
        User currentUser = userDataAccess.getCurrentUser();
        currentUser.setDarkMode(inputData.isDarkMode());
        userDataAccess.updateUserDarkMode(currentUser, inputData.isDarkMode());

        DarkModeOutputData outputData = new DarkModeOutputData(inputData.isDarkMode());
        outputBoundary.updateUIMode(outputData);
    }
}
