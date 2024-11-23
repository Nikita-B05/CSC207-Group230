package interface_adapter.homepage;

import entity.Avatar;
import entity.Decision;
import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInputData;

import java.util.ArrayList;

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
    public void switchToAvatarView(
            String username, String name, Avatar avatar, boolean isDarkMode, ArrayList<Decision> decisions) {
        final HomepageInputData homepageInputData = new HomepageInputData(
                username, name, avatar, isDarkMode, decisions);
        userHomepageUseCaseInteractor.switchToChooseAvatarView(homepageInputData);
    }

    /**
     * Executes the "switch to PlayGame" Use Case.
     */
    public void switchToPlayGameView(
            String username, String name, Avatar avatar, boolean isDarkMode, ArrayList<Decision> decisions) {
        final HomepageInputData homepageInputData = new HomepageInputData(
                username, name, avatar, isDarkMode, decisions);
        userHomepageUseCaseInteractor.switchToPlayGameView(homepageInputData);
    }

    /**
     * Executes the "switch to DecisionLog" Use Case.
     */
    public void switchToDecisionLogView(
            String username, String name, Avatar avatar, boolean isDarkMode, ArrayList<Decision> decisions) {
        final HomepageInputData homepageInputData = new HomepageInputData(
                username, name, avatar, isDarkMode, decisions);
        userHomepageUseCaseInteractor.switchToDecisionLogView(homepageInputData);
    }

    /**
     * Executes the "switch to ProfileSettings" Use Case.
     */
    public void switchToProfileSettingsView(
            String username, String name, Avatar avatar, boolean isDarkMode, ArrayList<Decision> decisions) {
        final HomepageInputData homepageInputData = new HomepageInputData(
                username, name, avatar, isDarkMode, decisions);
        userHomepageUseCaseInteractor.switchToProfileSettingsView(homepageInputData);
    }
}
