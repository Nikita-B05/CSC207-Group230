package interface_adapter.game_success;

import interface_adapter.ViewModel;

public class GameSuccessViewModel extends ViewModel<GameSuccessState> {

    public GameSuccessViewModel() {
        super("gameSuccess");
        setState(new GameSuccessState());
    }
}
