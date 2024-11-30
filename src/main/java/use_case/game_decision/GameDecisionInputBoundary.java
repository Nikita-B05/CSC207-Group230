package use_case.game_decision;

/**
 * Input Boundary for the Game Decision use case.
 */
public interface GameDecisionInputBoundary {
    void pickDecision(GameDecisionInputData gameDecisionInputData);
    void switchToAssetsManager(GameDecisionInputData gameDecisionInputData);
    void switchToBankruptcy(GameDecisionInputData gameDecisionInputData);
}
