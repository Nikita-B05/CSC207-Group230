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
    public void switchToAvatarView(String username, String name, Avatar avatar) {
        final HomepageInputData homepageInputData = new HomepageInputData(username, name, avatar);
        userHomepageUseCaseInteractor.switchToAvatarView(homepageInputData);
    }

    /**
     * Executes the "switch to PlayGame" Use Case.
     */
    public void switchToPlayGameView(String username, String name, Avatar avatar) {
        final HomepageInputData homepageInputData = new HomepageInputData(username, name, avatar);
        userHomepageUseCaseInteractor.switchToPlayGameView(homepageInputData);
    }

    /**
     * Executes the "switch to DecisionLog" Use Case.
     */
    public void switchToDecisionLogView(String username, String name, Avatar avatar) {
        HomepageInputData homepageInputData = new HomepageInputData(username, name, avatar);
        userHomepageUseCaseInteractor.switchToDecisionLogView(homepageInputData);
    }

    /**
     * Executes the "switch to ProfileSettings" Use Case.
     */
    public void switchToProfileSettingsView(String username, String name, Avatar avatar) {
        HomepageInputData homepageInputData = new HomepageInputData(username, name, avatar);
        userHomepageUseCaseInteractor.switchToProfileSettingsView(homepageInputData);
    }
}
