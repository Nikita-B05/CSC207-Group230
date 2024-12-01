package interface_adapter.homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_avatar.ChooseAvatarViewModel;
import interface_adapter.game_decision.GameDecisionViewModel;
import interface_adapter.settings.SettingsState;
import interface_adapter.settings.SettingsViewModel;
import use_case.homepage.HomepageOutputBoundary;
import use_case.homepage.HomepageOutputData;

/**
 * The Presenter for the Homepage Use Case.
 */
public class HomepagePresenter implements HomepageOutputBoundary {
    private final HomepageViewModel homepageViewModel;
//    private final DecisionLogViewModel decisionLogViewModel;
    private ChooseAvatarViewModel chooseAvatarViewModel = new ChooseAvatarViewModel();
    private final SettingsViewModel settingsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final GameDecisionViewModel gameDecisionViewModel;

    public HomepagePresenter(
        ViewManagerModel viewManagerModel,
        HomepageViewModel homepageViewModel,
        SettingsViewModel settingsViewModel,
        ChooseAvatarViewModel chooseAvatarViewModel,
        GameDecisionViewModel gameDecisionViewModel
//        DecisionLogViewModel decisionLogViewModel
        ) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.settingsViewModel = settingsViewModel;
        this.chooseAvatarViewModel = chooseAvatarViewModel;
        this.gameDecisionViewModel = gameDecisionViewModel;
//        this.decisionLogViewModel = decisionLogViewModel;
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
        gameDecisionViewModel.getState().setUsername(homepageOutputData.getUsername());
        gameDecisionViewModel.getState().setCharacterName(homepageOutputData.getCharacterName());
        gameDecisionViewModel.getState().setAge(homepageOutputData.getAge());
        gameDecisionViewModel.getState().setDarkModeEnabled(homepageOutputData.isDarkMode());
        gameDecisionViewModel.getState().setAvatar(homepageOutputData.getAvatar());
        gameDecisionViewModel.getState().setQuestion(homepageOutputData.getQuestion(homepageOutputData.getAge()));
        gameDecisionViewModel.firePropertyChanged();
        viewManagerModel.setState(gameDecisionViewModel.getViewName());
        homepageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToDecisionLogView(HomepageOutputData homepageOutputData) {
//        viewManagerModel.setState(decisionLogViewModel.getViewName());
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