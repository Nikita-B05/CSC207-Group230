package interface_adapter.homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_avatar.ChooseAvatarViewModel;
import interface_adapter.decision_log.DecisionLogViewModel;
import interface_adapter.settings.SettingsState;
import interface_adapter.settings.SettingsViewModel;
import use_case.homepage.HomepageOutputBoundary;
import use_case.homepage.HomepageOutputData;

/**
 * The Presenter for the Homepage Use Case.
 */
public class HomepagePresenter implements HomepageOutputBoundary {
    private final HomepageViewModel homepageViewModel;
    private final DecisionLogViewModel decisionLogViewModel;
//    private final ChooseAvatarViewModel chooseAvatarViewModel;
//    private final PlayGameViewModel playGameViewModel;
private ChooseAvatarViewModel chooseAvatarViewModel = new ChooseAvatarViewModel();
    private final SettingsViewModel settingsViewModel;
    private final ViewManagerModel viewManagerModel;

    public HomepagePresenter(
        ViewManagerModel viewManagerModel,
        HomepageViewModel homepageViewModel,
        SettingsViewModel settingsViewModel,
        ChooseAvatarViewModel chooseAvatarViewModel,
//        PlayGameViewModel playGameViewModel,
        DecisionLogViewModel decisionLogViewModel
        ) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.settingsViewModel = settingsViewModel;
        this.chooseAvatarViewModel = chooseAvatarViewModel;
        this.decisionLogViewModel = decisionLogViewModel;
//        this.playGameViewModel = playGameViewModel;
    }

    @Override
    public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
        chooseAvatarViewModel.getState().setUsername(homepageOutputData.getUsername());
        chooseAvatarViewModel.getState().setDarkMode(homepageOutputData.isDarkMode());
        chooseAvatarViewModel.getState().setAvatar(homepageOutputData.getAvatar());
        chooseAvatarViewModel.firePropertyChanged();
        viewManagerModel.setState(chooseAvatarViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPlayGameView(HomepageOutputData homepageOutputData) {
//        viewManagerModel.setState(playGameViewModel.getViewName());
        homepageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToDecisionLogView(HomepageOutputData homepageOutputData) {
        decisionLogViewModel.getState().setUsername(homepageOutputData.getUsername());
        decisionLogViewModel.getState().setDarkModeEnabled(homepageOutputData.isDarkMode());
        decisionLogViewModel.getState().setDecisions(homepageOutputData.getDecisions());
        viewManagerModel.setState(decisionLogViewModel.getViewName()); // Use the instance's getViewName method
        homepageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSettingsView(HomepageOutputData homepageOutputData) {
        viewManagerModel.setState(settingsViewModel.getViewName());
        final SettingsState state = settingsViewModel.getState();
        state.setUsername(homepageOutputData.getUsername());
        state.setDarkModeEnabled(homepageOutputData.isDarkMode());
        settingsViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}