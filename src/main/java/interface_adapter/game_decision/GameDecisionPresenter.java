package interface_adapter.game_decision;

import interface_adapter.ViewManagerModel;
import use_case.game_decision.GameDecisionOutputBoundary;
import use_case.game_decision.GameDecisionOutputData;
import interface_adapter.homepage.HomepageViewModel;
//import interface_adapter.asset_manager.AssetManagerViewModel;
//import interface_adapter.gameover.GameoverViewModel;

public class GameDecisionPresenter implements GameDecisionOutputBoundary {

    private final GameDecisionViewModel gameDecisionViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;
//    private final AssetManagerViewModel assetManagerViewModel;
//    private final GameoverViewModel gameoverViewModel;


    public GameDecisionPresenter(GameDecisionViewModel gameDecisionViewModel, ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel) {
        this.gameDecisionViewModel = gameDecisionViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
//        this.assetManagerViewModel = assetManagerViewModel;
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
//        assetManagerViewModel.getState().setDarkMode(outputData.isDarkMode());
//        assetManagerViewModel.firePropertyChanged();
//        viewManagerModel.setState(assetManagerViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareGameOverView(GameDecisionOutputData outputData) {
        // boop
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
