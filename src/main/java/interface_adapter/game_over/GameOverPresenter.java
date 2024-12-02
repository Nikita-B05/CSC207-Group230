package interface_adapter.game_over;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageViewModel;
import use_case.game_over.GameOverOutputBoundary;
import use_case.settings.SettingsOutputData;

public class GameOverPresenter implements GameOverOutputBoundary {
    private final ViewManagerModel viewManagerModel; // Represents the application's view manager
    private final HomepageViewModel homepageViewModel; // Represents the homepage data model

    public GameOverPresenter(ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel) {
        this.viewManagerModel = viewManagerModel; // Correctly assign the constructor parameter
        this.homepageViewModel = homepageViewModel; // Initialize the homepageViewModel
    }

    @Override
    public void switchToHomepageView(SettingsOutputData outputData) {
        homepageViewModel.getState().setUsername(outputData.getUsername());
        homepageViewModel.getState().setDarkMode(outputData.isDarkMode());
        homepageViewModel.getState().setAvatar(outputData.getAvatar());
        homepageViewModel.getState().setName(outputData.getName());
        homepageViewModel.firePropertyChanged();
    }
}
