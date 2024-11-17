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
        // If the password change was successful, fire a property change indicating that the password has changed.
        changePasswordViewModel.firePropertyChanged("passwordChanged");
    }

    @Override
    public void prepareFailView(String error) {
        // If the password change failed, update the error message in the state.
        ChangePasswordState state = changePasswordViewModel.getState();
        state.setPasswordError(error);

        // Notify the view about the error.
        changePasswordViewModel.firePropertyChanged("passwordError");

        // Clear the error after notifying, for subsequent use.
        state.setPasswordError(null);
        changePasswordViewModel.firePropertyChanged("passwordError");
    }
}
