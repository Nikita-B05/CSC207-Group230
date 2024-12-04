package interface_adapter.settings;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.settings.SettingsOutputBoundary;
import use_case.settings.SettingsOutputData;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.homepage.HomepageViewModel;

/**
 * Presenter for the Settings Use Case.
 */
public class SettingsPresenter implements SettingsOutputBoundary {
    private final SettingsViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final HomepageViewModel homepageViewModel;
    private final LoginViewModel loginViewModel;

    public SettingsPresenter(SettingsViewModel viewModel, ViewManagerModel viewManagerModel,
                             ChangePasswordViewModel changePasswordViewModel, HomepageViewModel homepageViewModel,
                             LoginViewModel loginViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.homepageViewModel = homepageViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareChangePasswordView(SettingsOutputData settingsOutputData) {
        viewManagerModel.setState(changePasswordViewModel.getViewName());
        changePasswordViewModel.getState().setUsername(settingsOutputData.getUsername());
        changePasswordViewModel.getState().setDarkModeEnabled(settingsOutputData.isDarkMode());
        changePasswordViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLogoutView(SettingsOutputData outputData) {
        viewModel.getState().setUsername(outputData.getUsername());
        viewModel.firePropertyChanged();
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareHomepageView(SettingsOutputData outputData) {
        homepageViewModel.getState().setUsername(outputData.getUsername());
        homepageViewModel.getState().setDarkMode(outputData.isDarkMode());
        homepageViewModel.getState().setAvatar(outputData.getAvatar());
        homepageViewModel.getState().setName(outputData.getName());
        homepageViewModel.firePropertyChanged();

        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
