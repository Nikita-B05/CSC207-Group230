package interface_adapter.game_decision;

import interface_adapter.ViewManagerModel;
import interface_adapter.asset_manager.AssetManagerViewModel;
import use_case.game_decision.GameDecisionOutputBoundary;
import use_case.game_decision.GameDecisionOutputData;
import interface_adapter.homepage.HomepageViewModel;
//import interface_adapter.asset_manager.AssetManagerViewModel;
//import interface_adapter.gameover.GameoverViewModel;

public class GameDecisionPresenter implements GameDecisionOutputBoundary {

    private final GameDecisionViewModel gameDecisionViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;
    private final AssetManagerViewModel assetManagerViewModel;
//    private final GameoverViewModel gameoverViewModel;


    public GameDecisionPresenter(
            GameDecisionViewModel gameDecisionViewModel,
            ViewManagerModel viewManagerModel,
            HomepageViewModel homepageViewModel,
            AssetManagerViewModel assetManagerViewModel
    ) {
        this.gameDecisionViewModel = gameDecisionViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.assetManagerViewModel = assetManagerViewModel;
//        this.gameoverViewModel = gameoverViewModel
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final GameDecisionState state = gameDecisionViewModel.getState();
        state.setDecisionError(errorMessage);
        gameDecisionViewModel.firePropertyChanged();
        state.setDecisionError(null);
        gameDecisionViewModel.firePropertyChanged();

    }

    @Override
    public void prepareAssetsView(GameDecisionOutputData outputData) {
        assetManagerViewModel.getState().setDarkMode(outputData.isDarkMode());
        assetManagerViewModel.firePropertyChanged();
        viewManagerModel.setState(assetManagerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
// username, name, avatar, cash, happiness
    @Override
    public void prepareGameOverView(GameDecisionOutputData outputData) {
//        gameOverViewModel.getState().setDarkModeEnabled(outputData.isDarkModeEnabled());
//        gameOverViewModel.getState().setName(outputData.getName());
//        gameOverViewModel.getState().setUsername(outputData.getUsername());
//        gameOverViewModel.getState().setAvatar(outputData.getAvatar());
//        gameOverViewModel.getState().setAssets(outputData.getAssets());
//        gameOverViewModel.getState().setHappiness(outputData.getHappiness());
//        gameOverViewModel.firePropertyChanged();
//        viewManagerModel.setState(gameOverViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareGameSuccessView(GameDecisionOutputData outputData) {
//        gameSuccessViewModel.getState().setDarkModeEnabled(outputData.isDarkModeEnabled());
//        gameSuccessViewModel.getState().setName(outputData.getName());
//        gameSuccessViewModel.getState().setUsername(outputData.getUsername());
//        gameSuccessViewModel.getState().setAvatar(outputData.getAvatar());
//        gameSuccessViewModel.getState().setAssets(outputData.getAssets());
//        gameSuccessViewModel.getState().setHappiness(outputData.getHappiness());
//        gameSuccessViewModel.firePropertyChanged();
//        viewManagerModel.setState(gameSuccessViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareHomepageView(GameDecisionOutputData outputData) {
        homepageViewModel.getState().setUsername(outputData.getUsername());
        homepageViewModel.getState().setDarkMode(outputData.isDarkMode());
        homepageViewModel.getState().setAvatar(outputData.getAvatar());
        homepageViewModel.getState().setName(outputData.getName());
        homepageViewModel.firePropertyChanged();

        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

}
