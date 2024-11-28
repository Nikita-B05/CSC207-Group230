package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.settings.SettingsViewModel;
import interface_adapter.settings.SettingsState;
import interface_adapter.homepage.HomepageState;
import interface_adapter.homepage.HomepageViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;
    private final SignupViewModel signupViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,
                          HomepageViewModel homepageViewModel,
                          SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.homepageViewModel = homepageViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        SettingsState newState = new SettingsState();
        newState.setUsername(response.getUsername());
        newState.setDarkModeEnabled(false);

        this.viewManagerModel.firePropertyChanged();
        final HomepageState homepageState = homepageViewModel.getState();
        homepageState.setUsername(response.getUsername());
        homepageState.setAvatar(response.getAvatar());
        homepageState.setName(response.getName());
        homepageState.setDarkMode(response.isDarkMode());
        this.homepageViewModel.setState(homepageState);
        this.homepageViewModel.firePropertyChanged();
        this.viewManagerModel.setState(homepageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged("loginError");
    }

    @Override
    public void switchToSignUpView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
