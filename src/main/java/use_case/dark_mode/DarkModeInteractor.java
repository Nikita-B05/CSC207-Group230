package use_case.dark_mode;

import data_access.InMemoryUserDataAccessObject;
import data_access.MongoDBUserDataAccessObject;
import entity.User;

/**
 * Dark Mode Interactor directly using DBUserDataAccessObject.
 */
public class DarkModeInteractor implements DarkModeInputBoundary {
    private final DarkModeOutputBoundary outputBoundary;
    private final InMemoryUserDataAccessObject userDataAccess;

    public DarkModeInteractor(DarkModeOutputBoundary outputBoundary, InMemoryUserDataAccessObject userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void toggleDarkMode(DarkModeInputData inputData) {
        User currentUser = userDataAccess.getCurrentUser();
        currentUser.setDarkMode(inputData.isDarkMode());
        userDataAccess.updateUserDarkMode(currentUser.isDarkMode());

        DarkModeOutputData outputData = new DarkModeOutputData(inputData.isDarkMode());
        outputBoundary.updateUIMode(outputData);
    }
}
