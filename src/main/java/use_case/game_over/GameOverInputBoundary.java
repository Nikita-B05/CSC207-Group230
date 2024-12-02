package use_case.game_over;

import use_case.game_decision.GameDecisionInputData;

/**
 * Input Boundary for the Game Over use case.
 */

public interface GameOverInputBoundary {
    void switchToHomeview(GameOverInputData gameOverInputData);
}
