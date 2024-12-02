package interface_adapter.decision_log;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.homepage.HomepageState;
import use_case.decision_log.DecisionLogOutputBoundary;
import use_case.decision_log.DecisionLogOutputData;
import use_case.game_decision.GameDecisionOutputData;

/**
 * Presenter for the Decision Log Use Case.
 */
public class DecisionLogPresenter implements DecisionLogOutputBoundary {
    private final DecisionLogViewModel decisionLogViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;

    public DecisionLogPresenter(
            DecisionLogViewModel decisionLogViewModel,
            ViewManagerModel viewManagerModel,
            HomepageViewModel homepageViewModel
    ) {
        this.decisionLogViewModel = decisionLogViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
    }

    @Override
    public void switchToHomepageView(DecisionLogOutputData decisionLogOutputData) {
        // Update the decision log view model first
        DecisionLogState state = decisionLogViewModel.getState();
        state.setUsername(decisionLogOutputData.getUsername());
        state.setDecisions(decisionLogOutputData.getDecisions());
        state.setDarkModeEnabled(decisionLogOutputData.isDarkMode());
        decisionLogViewModel.firePropertyChanged();

        // Then handle the homepage transition
        HomepageState homepageState = homepageViewModel.getState();
        homepageState.setUsername(decisionLogOutputData.getUsername());
        homepageState.setDarkMode(decisionLogOutputData.isDarkMode());
        homepageViewModel.firePropertyChanged();

        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

