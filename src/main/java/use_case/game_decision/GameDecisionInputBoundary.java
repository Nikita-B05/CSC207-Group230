package use_case.game_decision;

/**
 * Input Boundary for the Game Decision use case.
 */
public interface GameDecisionInputBoundary {

    void switchToAssetsManager(GameDecisionInputData gameDecisionInputData);

    void switchToGameOver(GameDecisionInputData gameDecisionInputData);

    void switchToHomeview(GameDecisionInputData gameDecisionInputData);

    void switchToGameSuccess(GameDecisionInputData inputData);
}
