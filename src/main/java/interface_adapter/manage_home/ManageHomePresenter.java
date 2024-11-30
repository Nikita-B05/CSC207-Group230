package interface_adapter.manage_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.asset_manager.AssetManagerState;
import interface_adapter.asset_manager.AssetManagerViewModel;
import use_case.manage_home.ManageHomeOutputBoundary;
import use_case.manage_home.ManageHomeOutputData;

/**
 * The Presenter for the Manage Home Use Case.
 */
public class ManageHomePresenter implements ManageHomeOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ManageHomeViewModel manageHomeViewModel;
    private final AssetManagerViewModel assetManagerViewModel;

    public ManageHomePresenter(
            ViewManagerModel viewManagerModel,
            ManageHomeViewModel manageHomeViewModel,
            AssetManagerViewModel assetManagerViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.manageHomeViewModel = manageHomeViewModel;
        this.assetManagerViewModel = assetManagerViewModel;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        ManageHomeState manageHomeState = manageHomeViewModel.getState();
        manageHomeState.setErrorMessage(errorMessage);
        manageHomeViewModel.firePropertyChanged("manageHomeError");
    }

    @Override
    public void prepareBuySuccessView(String successMessage, double newHome) {
        ManageHomeState manageHomeState = manageHomeViewModel.getState();
        manageHomeState.setHome(newHome);
        manageHomeState.setSuccessMessage(successMessage);
        manageHomeViewModel.firePropertyChanged("manageHomeBuySuccess");
        this.manageHomeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSellSuccessView(String successMessage) {
        ManageHomeState manageHomeState = manageHomeViewModel.getState();
        manageHomeState.setHome(0.0);
        manageHomeState.setSuccessMessage(successMessage);
        manageHomeViewModel.firePropertyChanged("manageHomeSellSuccess");
        this.manageHomeViewModel.firePropertyChanged();
    }

    @Override
    public void switchToAssetManagerView(ManageHomeOutputData outputData) {
        viewManagerModel.setState(assetManagerViewModel.getViewName());
        final AssetManagerState state = assetManagerViewModel.getState();
        state.setDarkMode(outputData.isDarkMode());
        assetManagerViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
