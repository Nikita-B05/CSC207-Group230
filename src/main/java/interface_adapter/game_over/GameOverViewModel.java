package interface_adapter.game_over;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Game Decision View.
 */
public class GameOverViewModel extends ViewModel<GameOverState>{
    public GameOverViewModel() {
        super("gameOver");
        setState(new GameOverState());

    }
}
