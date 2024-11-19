package interface_adapter.homepage;

import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Homepage Use Case.
 */
public class HomepageController {
    private final HomepageInputBoundary userHomepageUseCaseInteractor;

    public HomepageController(HomepageInputBoundary userHomepageUseCaseInteractor) {
        this.userHomepageUseCaseInteractor = userHomepageUseCaseInteractor;
    }

    /**
     * Executes the "switch to ChooseCharacter" Use Case.
     */
    public void switchToAvatarView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        userHomepageUseCaseInteractor.switchToChooseAvatarView(homepageInputData);
    }

    /**
     * Executes the "switch to PlayGame" Use Case.
     */
    public void switchToPlayGameView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        userHomepageUseCaseInteractor.switchToPlayGameView(homepageInputData);
    }

    /**
     * Executes the "switch to DecisionLog" Use Case.
     */
    public void switchToDecisionLogView(String username) {
        HomepageInputData homepageInputData = new HomepageInputData(username);
        userHomepageUseCaseInteractor.switchToDecisionLogView(homepageInputData);
    }

    /**
     * Executes the "switch to ProfileSettings" Use Case.
     */
    public void switchToProfileSettingsView(String username) {
        HomepageInputData homepageInputData = new HomepageInputData(username);
        userHomepageUseCaseInteractor.switchToProfileSettingsView(homepageInputData);
    }
}
