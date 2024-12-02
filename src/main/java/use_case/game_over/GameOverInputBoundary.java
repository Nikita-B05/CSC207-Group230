package use_case.game_over;

public interface GameOverInputBoundary {
    void execute(GameOverInputData inputData);

    void cleanupPlayerData(String username);
}

