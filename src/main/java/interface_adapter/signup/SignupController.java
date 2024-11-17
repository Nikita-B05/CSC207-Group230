package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String password1, String password2) {
        System.out.println("Executing Signup Use Case with:");
        System.out.println("Username: " + username);
        System.out.println("Password1: " + password1);
        System.out.println("Password2: " + password2);
        final SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);
        System.out.println("SignupInputData created with:");
        System.out.println("Username: " + signupInputData.getUsername());
        System.out.println("Password1: " + signupInputData.getPassword());
        System.out.println("Password2: " + signupInputData.getRepeatPassword());
        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
}
