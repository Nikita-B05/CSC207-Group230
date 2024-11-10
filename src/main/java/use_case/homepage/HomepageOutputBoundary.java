package use_case.homepage;

import use_case.login.LoginOutputData;

/**
 * The output boundary for the Homepage Use Case.
 */
public interface HomepageOutputBoundary {
    /**
     * Switches to the Character View.
     */
    void switchToCharacterView();

    /**
     * Switches to the Game View.
     */
    void switchToGameView();

    /**
     * Switches to the Decision View.
     */
    void switchToDecisionView();

    /**
     * Switches to the Profile View.
     */
    void switchToProfileView();
}
