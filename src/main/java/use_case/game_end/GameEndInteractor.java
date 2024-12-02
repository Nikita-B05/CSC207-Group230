package use_case.game_end;

public class GameEndInteractor implements GameEndInputBoundary {
    private final GameEndOutputBoundary presenter;

    public GameEndInteractor(GameEndOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handleGameEnd(GameEndInputData inputData) {
        String message = inputData.isSuccess()
                ? "Congratulations! You won!"
                : "Game Over. Better luck next time.";

        GameEndOutputData outputData = new GameEndOutputData(message);
        presenter.prepareGameEndView(outputData);
    }
}

