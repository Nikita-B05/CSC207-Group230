package interface_adapter.game_success;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageViewModel;
import use_case.game_success.GameSuccessOutputBoundary;
import use_case.game_success.GameSuccessOutputData;

public class GameSuccessPresenter implements GameSuccessOutputBoundary {
    private GameSuccessViewModel gameSuccessViewModel;
    private HomepageViewModel homepageViewModel;
    private ViewManagerModel viewManagerModel;

    public GameSuccessPresenter(
            GameSuccessViewModel gameSuccessViewModel,
            HomepageViewModel homepageViewModel,
            ViewManagerModel viewManagerModel
    ) {
        this.gameSuccessViewModel = gameSuccessViewModel;
        this.homepageViewModel = homepageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareHomepageView(GameSuccessOutputData outputData) {
        homepageViewModel.getState().setUsername(outputData.getUsername());
        homepageViewModel.getState().setName(outputData.getCharacterName());
        homepageViewModel.getState().setDarkMode(outputData.isDarkMode());
        homepageViewModel.getState().setAvatar(outputData.getAvatar());
        homepageViewModel.firePropertyChanged();
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
