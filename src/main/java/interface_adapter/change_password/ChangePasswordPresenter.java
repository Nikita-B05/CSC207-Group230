package interface_adapter.change_password;

import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ChangePasswordViewModel changePasswordViewModel;

    public ChangePasswordPresenter(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        changePasswordViewModel.firePropertyChanged("passwordChanged");
    }

    @Override
    public void prepareFailView(String error) {
        final LoggedInState loggedInState = changePasswordViewModel.getState();
        loggedInState.setPasswordError(error);
        changePasswordViewModel.firePropertyChanged();
        loggedInState.setPasswordError(null);
        changePasswordViewModel.firePropertyChanged();
    }
}
