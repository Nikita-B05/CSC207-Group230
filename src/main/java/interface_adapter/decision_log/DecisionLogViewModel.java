package interface_adapter.decision_log;

import interface_adapter.ViewModel;

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

