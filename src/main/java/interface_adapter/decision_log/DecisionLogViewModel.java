package interface_adapter.decision_log;

import entity.Decision;
import interface_adapter.ViewModel;

import java.util.List;

/**
 * ViewModel for the Decision Log View.
 */
public class DecisionLogViewModel extends ViewModel<DecisionLogState> {

    public static final String VIEW_NAME = "decision_log";

    public DecisionLogViewModel() {
        super(VIEW_NAME);
        setState(new DecisionLogState());
    }

    public Object getTotalNetWorthChange() {
        return getState().getTotalNetWorthChange();
    }

    public Object getTotalHappinessChange() {
        return getState().getTotalHappinessChange();
    }

    public List<Decision> getDecisions() {
        return getState().getDecisions();
    }

    public boolean isDarkModeEnabled() {
        return getState().isDarkModeEnabled();
    }

    public void setDecisions(List<Decision> decisions) { getState().setDecisions(decisions); }

    public void setDarkModeEnabled(boolean b) { getState().setDarkModeEnabled(b); }

    public String getUsername() { return getState().getUsername(); }
}

