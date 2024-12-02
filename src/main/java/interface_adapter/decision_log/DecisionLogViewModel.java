package interface_adapter.decision_log;

import entity.Decision;
import interface_adapter.ViewModel;
import interface_adapter.asset_manager.AssetManagerState;

import java.util.List;

/**
 * ViewModel for the Decision Log View.
 */
public class DecisionLogViewModel extends ViewModel<DecisionLogState> {

    public static final String TITLE = "Decision Log";

    public DecisionLogViewModel() {
        super("decisionLog");
        setState(new DecisionLogState());
    }
}

