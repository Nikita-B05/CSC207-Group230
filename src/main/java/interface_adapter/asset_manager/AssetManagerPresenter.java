package interface_adapter.asset_manager;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_decision.GameDecisionViewModel;
import interface_adapter.manage_home.ManageHomeState;
import interface_adapter.manage_home.ManageHomeViewModel;
import interface_adapter.manage_stock.ManageStockState;
import interface_adapter.manage_stock.ManageStockViewModel;
import use_case.choose_asset.ChooseAssetOutputBoundary;
import use_case.choose_asset.ChooseAssetOutputData;

public class AssetManagerPresenter implements ChooseAssetOutputBoundary {

    private final AssetManagerViewModel assetManagerViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ManageHomeViewModel manageHomeViewModel;
    private final ManageStockViewModel manageStockViewModel;
    private final GameDecisionViewModel gameDecisionViewModel;

    public AssetManagerPresenter(
            AssetManagerViewModel assetManagerViewModel,
            ViewManagerModel viewManagerModel,
            ManageHomeViewModel manageHomeViewModel,
            ManageStockViewModel manageStockViewModel,
            GameDecisionViewModel gameDecisionViewModel
    ) {
        this.assetManagerViewModel = assetManagerViewModel;
        this.viewManagerModel = viewManagerModel;
        this.manageHomeViewModel = manageHomeViewModel;
        this.manageStockViewModel = manageStockViewModel;
        this.gameDecisionViewModel = gameDecisionViewModel;
    }

    @Override
    public void switchToManageHomeView(ChooseAssetOutputData chooseAssetOutputData) {
        final ManageHomeState state = manageHomeViewModel.getState();
        state.setHome(chooseAssetOutputData.getHome());
        state.setDarkMode(chooseAssetOutputData.isDarkMode());
        state.setAvailableCash(chooseAssetOutputData.getCash());
        manageHomeViewModel.setState(state);
        manageHomeViewModel.firePropertyChanged();

        viewManagerModel.setState(manageHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToManageStockView(ChooseAssetOutputData chooseAssetOutputData) {
        final ManageStockState state = manageStockViewModel.getState();
        state.setQuantity(null);
        state.setDarkMode(chooseAssetOutputData.isDarkMode());
        state.setNameToCode(chooseAssetOutputData.getNameToCode());
        state.setCodeToPrice(chooseAssetOutputData.getCodeToPrice());
        state.setStockNames(chooseAssetOutputData.getStockNames());
        state.setCode(chooseAssetOutputData.getNameToCode().get(chooseAssetOutputData.getStockNames()[0]));
        state.setCash(chooseAssetOutputData.getCash());
        state.setQuantity("");
        manageStockViewModel.setState(state);
        manageStockViewModel.firePropertyChanged();

        viewManagerModel.setState(manageStockViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameDecisionView(ChooseAssetOutputData chooseAssetOutputData) {
        gameDecisionViewModel.getState().setAge(chooseAssetOutputData.getAge());
        gameDecisionViewModel.getState().setAvatar(chooseAssetOutputData.getAvatar());
        gameDecisionViewModel.getState().setCharacterName(chooseAssetOutputData.getCharacterName());
        gameDecisionViewModel.getState().setQuestion(chooseAssetOutputData.getQuestion());
        gameDecisionViewModel.getState().setDarkModeEnabled(chooseAssetOutputData.isDarkMode());
        gameDecisionViewModel.getState().setStockPrices(chooseAssetOutputData.getStockPrices());
        gameDecisionViewModel.getState().setSalary(chooseAssetOutputData.getSalary());
        gameDecisionViewModel.getState().setAssets(chooseAssetOutputData.getAssets());
        gameDecisionViewModel.getState().setHappiness(chooseAssetOutputData.getHappiness());
        gameDecisionViewModel.firePropertyChanged();
        viewManagerModel.setState(gameDecisionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
