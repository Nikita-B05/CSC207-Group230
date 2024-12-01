package interface_adapter.decision_log;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Decision Log View.
 */
public class DecisionLogViewModel extends ViewModel<DecisionLogState> {
    public static final String VIEW_NAME = "decision_log";

    public DecisionLogViewModel() {
        super(VIEW_NAME);
        setState(new DecisionLogState());
    }
}

