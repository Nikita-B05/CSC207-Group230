package interface_adapter.settings;

import interface_adapter.ViewManagerModel;
import use_case.settings.SettingsOutputBoundary;
import use_case.settings.SettingsOutputData;

/**
 * Presenter for the Settings Use Case.
 */
public class SettingsPresenter implements SettingsOutputBoundary {
    private final SettingsViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public SettingsPresenter(SettingsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareChangePasswordView() {
        viewManagerModel.setState("changePassword");
        viewManagerModel.firePropertyChanged("viewChange");
    }

    @Override
    public void prepareLogoutView(SettingsOutputData outputData) {
        viewModel.getState().setUsername(outputData.getUsername());
        viewModel.firePropertyChanged("logout");
    }

    @Override
    public void updateDarkMode(boolean darkModeEnabled) {
        viewModel.getState().setDarkModeEnabled(darkModeEnabled);
        viewModel.firePropertyChanged("darkMode");
    }
}
