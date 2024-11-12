package interface_adapter.homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;
import use_case.homepage.HomepageOutputBoundary;
import use_case.homepage.HomepageOutputData;

/**
 * The Presenter for the Homepage Use Case.
 */
public class HomepagePresenter implements HomepageOutputBoundary {
    private final HomepageViewModel homepageViewModel;
    private final ChooseAvatarViewModel chooseAvatarViewModel;
    private final PlayGameViewModel playGameViewModel;
    private final DecisionLogViewModel decisionLogViewModel;
    private final ProfileSettingsViewModel profileSettingsViewModel;
    private final ViewManagerModel viewManagerModel;

    public HomepagePresenter(HomepageViewModel homepageViewModel) {
        this.homepageViewModel = homepageViewModel;
    }

    @Override
    public void switchToAvatarView(HomepageOutputData homepageOutputData) {
        viewManagerModel.setState(chooseAvatarViewModel.getViewName());
        homepageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPlayGameView(HomepageOutputData homepageOutputData) {
        viewManagerModel.setState(playGameViewModel.getViewName());
        homepageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToDecisionLogView(HomepageOutputData homepageOutputData) {
        viewManagerModel.setState(decisionLogViewModel.getViewName());
        homepageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileSettingsView(HomepageOutputData homepageOutputData) {
        viewManagerModel.setState(profileSettingsViewModel.getViewName());
        homepageViewModel.firePropertyChanged();
    }
}