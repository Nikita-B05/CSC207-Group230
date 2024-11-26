package interface_adapter.settings;

import entity.CommonUser;
import interface_adapter.ViewManagerModel;
import use_case.settings.SettingsOutputBoundary;
import use_case.settings.SettingsOutputData;
import interface_adapter.change_password.ChangePasswordViewModel;


/**
 * Presenter for the Settings Use Case.
 */
public class SettingsPresenter implements SettingsOutputBoundary {
    private final SettingsViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;

    public SettingsPresenter(SettingsViewModel viewModel, ViewManagerModel viewManagerModel, ChangePasswordViewModel changePasswordViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
    }

    @Override
    public void prepareChangePasswordView() {
        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLogoutView(SettingsOutputData outputData) {
        viewModel.getState().setUsername(outputData.getUsername());
        viewModel.firePropertyChanged();

        viewManagerModel.setState("log in");
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void updateDarkMode(boolean darkModeEnabled) {
        viewModel.getState().setDarkModeEnabled(darkModeEnabled);
        viewModel.firePropertyChanged();
    }
}
