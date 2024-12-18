package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The View Model for the Change Password View.
 */
public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    public ChangePasswordViewModel() {
        super("changePassword");
        setState(new ChangePasswordState());
    }
}
