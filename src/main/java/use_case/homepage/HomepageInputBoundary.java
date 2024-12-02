package use_case.homepage;

/**
 * Input Boundary for actions which are related to the homepage.
 */
public interface HomepageInputBoundary {
    /**
     * Executes the switch to Character view use case.
     * @param homepageInputData the input data for the use case.
     */
    void switchToChooseAvatarView(HomepageInputData homepageInputData);

    /**
     * Executes the switch to Game view use case.
     * @param homepageInputData the input data for the use case.
     */
    void switchToPlayGameView(HomepageInputData homepageInputData);

    /**
     * Executes the switch to Decision view use case.
     * @param homepageInputData the input data for the use case.
     */
    void switchToDecisionLogView(HomepageInputData homepageInputData);

    /**
     * Executes the switch to Profile view use case.
     * @param homepageInputData the input data for the use case.
     */
    void switchToProfileSettingsView(HomepageInputData homepageInputData);
}
