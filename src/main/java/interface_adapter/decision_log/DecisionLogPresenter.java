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

    public void switchToHomepageView(GameDecisionOutputData outputData) {
        homepageViewModel.getState().setUsername(outputData.getUsername());
        homepageViewModel.getState().setDarkMode(outputData.isDarkMode());
        homepageViewModel.getState().setAvatar(outputData.getAvatar());
        homepageViewModel.getState().setName(outputData.getName());
        homepageViewModel.firePropertyChanged();

        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void switchToHomepageView(DecisionLogOutputData decisionLogOutputData) {
        HomepageState state = homepageViewModel.getState();
        state.setUsername(decisionLogOutputData.getUsername());
        state.setDarkMode(decisionLogOutputData.isDarkMode());
        state.setAvatar(decisionLogOutputData.getAvatar());
        state.setName(decisionLogOutputData.getName());
        homepageViewModel.setState(state);
        homepageViewModel.firePropertyChanged();

        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

