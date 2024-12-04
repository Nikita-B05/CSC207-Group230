package use_case.game_over;

import use_case.game_decision.GameDecisionOutputData;

/**
 * The output boundary for the Game Decision Use Case.
 */
public interface GameOverOutputBoundary {

    void prepareHomepageView(GameOverOutputData outputData);
}
