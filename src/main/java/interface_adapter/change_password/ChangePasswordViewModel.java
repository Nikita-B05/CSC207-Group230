package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Change Password View.
 */
public class ChangePasswordViewModel extends ViewModel<LoggedInState> {

    public ChangePasswordViewModel() {
        super("changePassword");
        setState(new LoggedInState());
    }
}
