package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.settings.SettingsViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ChangePasswordViewModel changePasswordViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SettingsViewModel settingsViewModel;

    public ChangePasswordPresenter(ChangePasswordViewModel changePasswordViewModel,
                                   ViewManagerModel viewManagerModel,
                                   SettingsViewModel settingsViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.viewManagerModel = viewManagerModel;
        this.settingsViewModel = settingsViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        ChangePasswordState state = changePasswordViewModel.getState();
        state.setPasswordError(null);
        state.setUsername(outputData.getUsername());
        changePasswordViewModel.setState(new ChangePasswordState(state));
        changePasswordViewModel.firePropertyChanged("password");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ChangePasswordState state = changePasswordViewModel.getState();
        state.setPasswordError(errorMessage);
        changePasswordViewModel.firePropertyChanged();
        state.setPasswordError(null);
        changePasswordViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSettingsView() {
        viewManagerModel.setState(settingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
