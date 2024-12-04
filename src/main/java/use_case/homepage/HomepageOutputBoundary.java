package use_case.homepage;

/**
 * The output boundary for the Homepage Use Case.
 */
public interface HomepageOutputBoundary {
    /**
     * Switches to the Character View.
     * @param homepageOutputData represents output view
     */
    void switchToChooseAvatarView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the Game View.
     * @param homepageOutputData represents output view
     */
    void switchToPlayGameView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the Decision View.
     * @param homepageOutputData represents output view
     */
    void switchToDecisionLogView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the Profile View.
     * @param homepageOutputData represents output view
     */
    void switchToSettingsView(HomepageOutputData homepageOutputData);
}
