package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {

    private final ChangePasswordInputBoundary changePasswordInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary changePasswordInteractor) {
        this.changePasswordInteractor = changePasswordInteractor;
    }

    public void execute(String password, String username) {
        final ChangePasswordInputData inputData = new ChangePasswordInputData(username, password);
        changePasswordInteractor.execute(inputData);
    }

    public void switchToSettingsView() {
        changePasswordInteractor.switchToSettingsView();
    }
}
