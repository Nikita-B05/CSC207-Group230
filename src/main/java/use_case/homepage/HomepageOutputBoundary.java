package use_case.homepage;

/**
 * The output boundary for the Homepage Use Case.
 */
public interface HomepageOutputBoundary {
    /**
     * Switches to the Character View.
     */
    void switchToChooseAvatarView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the Game View.
     */
    void switchToPlayGameView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the Decision View.
     */
    void switchToDecisionLogView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the Profile View.
     */
    void switchToSettingsView(HomepageOutputData homepageOutputData);
}
