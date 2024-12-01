package interface_adapter.game_decision;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Game Decision View.
 */
public class GameDecisionViewModel extends ViewModel<GameDecisionState>{

    public GameDecisionViewModel() {
        super("game decision");
        setState(new GameDecisionState());
    }
}
