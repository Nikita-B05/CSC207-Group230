package use_case.homepage;

/**
 * Input Boundary for actions which are related to the homepage.
 */
import use_case.logout.LogoutInputData;

public interface HomepageInputBoundary {
    /**
     * Executes the switch to Character view use case.
     */
    void switchToCharacterView();

    /**
     * Executes the switch to Game view use case.
     */
    void switchToGameView();

    /**
     * Executes the switch to Decision view use case.
     */
    void switchToDecisionView();

    /**
     * Executes the switch to Profile view use case.
     */
    void switchToProfileView();
}
