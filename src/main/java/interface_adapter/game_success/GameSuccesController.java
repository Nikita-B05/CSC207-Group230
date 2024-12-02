package interface_adapter.game_success;

import use_case.game_success.GameSuccessInputBoundary;

public class GameSuccesController {
    private GameSuccessInputBoundary gameSuccessInteractor;

    public GameSuccesController(GameSuccessInputBoundary gameSuccessInputBoundary) {
        this.gameSuccessInteractor = gameSuccessInputBoundary;
    }

    public void switchToHomepageView() {
        gameSuccessInteractor.switchToHomepageView();
    }
}
