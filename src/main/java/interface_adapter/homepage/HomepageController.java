package interface_adapter.homepage;

import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInputData;

/**
 * Controller for the Homepage Use Case.
 */
public class HomepageController {
    private final HomepageInputBoundary userHomepageUseCaseInteractor;

    public HomepageController(HomepageInputBoundary userHomepageUseCaseInteractor) {
        this.userHomepageUseCaseInteractor = userHomepageUseCaseInteractor;
    }

    /**
     * Executes the "switch to ChooseAvatar" Use Case.
     * @param username the username of the current user
     */
    public void switchToAvatarView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        userHomepageUseCaseInteractor.switchToChooseAvatarView(homepageInputData);
    }

    /**
     * Executes the "switch to PlayGame" Use Case.
     * @param username the username of the current user
     */
    public void switchToPlayGameView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        userHomepageUseCaseInteractor.switchToPlayGameView(homepageInputData);
    }

    /**
     * Executes the "switch to DecisionLog" Use Case.
     * @param username the username of the current user
     */
    public void switchToDecisionLogView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        userHomepageUseCaseInteractor.switchToDecisionLogView(homepageInputData);
    }

    /**
     * Executes the "switch to ProfileSettings" Use Case.
     * @param username the username of the current user
     */
    public void switchToProfileSettingsView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        userHomepageUseCaseInteractor.switchToProfileSettingsView(homepageInputData);
    }
}
