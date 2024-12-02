package use_case.game_over;

public interface GameOverOutputBoundary {
    void prepareSuccessView(GameOverOutputData outputData);
    void prepareFailView(String error);
}

