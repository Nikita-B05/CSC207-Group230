package interface_adapter.game_over;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageViewModel;
import use_case.game_over.GameOverOutputBoundary;
import use_case.game_over.GameOverOutputData;

public class GameOverPresenter implements GameOverOutputBoundary {

    private final GameOverViewModel gameOverViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;


    public GameOverPresenter(GameOverViewModel gameOverViewModel, ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel) {
        this.gameOverViewModel = gameOverViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
    }

    @Override
    public void prepareHomepageView(GameOverOutputData outputData) {
        homepageViewModel.getState().setUsername(outputData.getUsername());
        homepageViewModel.getState().setDarkMode(gameOverViewModel.getState().isDarkModeEnabled());
        homepageViewModel.getState().setAvatar(outputData.getAvatar());
        homepageViewModel.getState().setName(outputData.getCharacterName());
        homepageViewModel.firePropertyChanged();

        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

}
