package interface_adapter.decision_log;

import interface_adapter.ViewManagerModel;
import use_case.decision_log.DecisionLogOutputBoundary;
import use_case.decision_log.DecisionLogOutputData;
import use_case.homepage.HomepageOutputData;

/**
 * Presenter for the Decision Log Use Case.
 */
public class DecisionLogPresenter implements DecisionLogOutputBoundary {
    private final DecisionLogViewModel viewModel;
    private final ViewManagerModel viewManager;

    public DecisionLogPresenter(DecisionLogViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void switchToDecisionLogView(DecisionLogOutputData outputData) {
        viewModel.getState().setUsername(outputData.getUsername());
        viewModel.getState().setDecisions(outputData.getDecisions());
        viewModel.firePropertyChanged();
        viewManager.setState(viewModel.getViewName());
        viewManager.firePropertyChanged();
    }

    @Override
    public void switchToHomepageView() {
        viewManager.setState("HomePage");
        viewManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String s) {

    }

    @Override
    public void prepareSuccessView(DecisionLogOutputData outputData) {

    }
}

