package interface_adapter.settings;

import use_case.settings.SettingsOutputBoundary;
import use_case.settings.SettingsOutputData;

/**
 * Presenter for the Settings Use Case.
 */
public class SettingsPresenter implements SettingsOutputBoundary {
    private final SettingsViewModel viewModel;

    public SettingsPresenter(SettingsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareChangePasswordView() {
        viewModel.firePropertyChanged("navigateToChangePassword");
    }

    @Override
    public void prepareLogoutView(SettingsOutputData outputData) {
        viewModel.getState().setUsername(outputData.getUsername());
        viewModel.firePropertyChanged("logout");
    }
}
