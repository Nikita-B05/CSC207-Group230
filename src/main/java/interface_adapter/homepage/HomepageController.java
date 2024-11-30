package interface_adapter.homepage;

import use_case.homepage.HomepageInputBoundary;

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
     */
    public void switchToAvatarView() {
        userHomepageUseCaseInteractor.switchToChooseAvatarView();
    }

    /**
     * Executes the "switch to PlayGame" Use Case.
     */
    public void switchToPlayGameView() {
        userHomepageUseCaseInteractor.switchToPlayGameView();
    }

    /**
     * Executes the "switch to DecisionLog" Use Case.
     */
    public void switchToDecisionLogView() {
        userHomepageUseCaseInteractor.switchToDecisionLogView();
    }

    /**
     * Executes the "switch to ProfileSettings" Use Case.
     */
    public void switchToProfileSettingsView() {
        userHomepageUseCaseInteractor.switchToProfileSettingsView();
    }
}
