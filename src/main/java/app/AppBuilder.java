package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//import data_access.InMemoryUserDataAccessObject;
import data_access.MongoDBUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.choose_avatar.ChooseAvatarController;
import interface_adapter.choose_avatar.ChooseAvatarPresenter;
import interface_adapter.choose_avatar.ChooseAvatarViewModel;
import interface_adapter.dark_mode.DarkModeController;
import interface_adapter.homepage.HomepageController;
import interface_adapter.homepage.HomepagePresenter;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.input_name.InputNameController;
import interface_adapter.input_name.InputNamePresenter;
import interface_adapter.input_name.InputNameViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.settings.SettingsController;
import interface_adapter.settings.SettingsPresenter;
import interface_adapter.settings.SettingsViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.choose_avatar.ChooseAvatarInputBoundary;
import use_case.choose_avatar.ChooseAvatarInteractor;
import use_case.choose_avatar.ChooseAvatarOutputBoundary;
import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInteractor;
import use_case.homepage.HomepageOutputBoundary;
import use_case.input_name.InputNameInputBoundary;
import use_case.input_name.InputNameInteractor;
import use_case.input_name.InputNameOutputBoundary;
import use_case.dark_mode.DarkModeInputBoundary;
import use_case.dark_mode.DarkModeInteractor;
import use_case.dark_mode.DarkModeOutputBoundary;
import use_case.dark_mode.DarkModeOutputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.settings.SettingsInputBoundary;
import use_case.settings.SettingsInteractor;
import use_case.settings.SettingsOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our Clean Architecture; piece by piece.
 *
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final MongoDBUserDataAccessObject userDataAccessObject = new MongoDBUserDataAccessObject(new CommonUserFactory());

    // Existing Views and ViewModels
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoginView loginView;
    private HomepageViewModel homepageViewModel;
    private HomepageView homepageView;
    private ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordView changePasswordView;
    private SettingsView settingsView;
    private SettingsViewModel settingsViewModel;

    // New Views and ViewModels for Choose Avatar and Input Name
    private ChooseAvatarViewModel chooseAvatarViewModel;
    private ChooseAvatarView chooseAvatarView;
    private InputNameViewModel inputNameViewModel;
    private InputNameView inputNameView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        userDataAccessObject.save(new CommonUser("testUser"));
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Homepage View to the application.
     * @return this builder
     */
    public AppBuilder addHomepageView() {
        homepageViewModel = new HomepageViewModel();
        homepageView = new HomepageView(homepageViewModel);
        cardPanel.add(homepageView, homepageView.getViewName());
        return this;
    }

    /**
     * Adds the Choose Avatar View to the application.
     * @return this builder
     */
    public AppBuilder addChooseAvatarView() {
        chooseAvatarViewModel = new ChooseAvatarViewModel();
        chooseAvatarView = new ChooseAvatarView(chooseAvatarViewModel);
        cardPanel.add(chooseAvatarView, chooseAvatarView.getViewName());
        return this;
    }

    /**
     * Adds the Input Name View to the application.
     * @return this builder
     */
    public AppBuilder addInputNameView() {
        inputNameViewModel = new InputNameViewModel();
        inputNameView = new InputNameView(inputNameViewModel);
        cardPanel.add(inputNameView, inputNameView.getViewName());
        return this;
    }

    /**
     * Adds the Change Password View to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordView() {
        changePasswordViewModel = new ChangePasswordViewModel();
        changePasswordView = new ChangePasswordView(changePasswordViewModel);
        cardPanel.add(changePasswordView, changePasswordView.getViewName());
        return this;
    }

    /**
     * Adds the Settings View to the application.
     * @return this builder
     */
    public AppBuilder addSettingsView() {
        settingsViewModel = new SettingsViewModel();

        DarkModeInputBoundary darkModeInteractor = new DarkModeInteractor(new DarkModeOutputBoundary() {
            @Override
            public void updateUIMode(DarkModeOutputData outputData) {
                settingsViewModel.setDarkMode(outputData.isDarkMode());
            }
        }, userDataAccessObject);
        DarkModeController darkModeController = new DarkModeController(darkModeInteractor);
        LogoutController logoutController = new LogoutController(new LogoutInteractor(userDataAccessObject, new LogoutPresenter(viewManagerModel, settingsViewModel, loginViewModel)));
        settingsView = new SettingsView(settingsViewModel, darkModeController, logoutController);
        cardPanel.add(settingsView, settingsView.getViewName());

        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary, userFactory);
        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loginViewModel, homepageViewModel, signupViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Homepage Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHomepageUseCase() {
        // Updated to include ChooseAvatarViewModel in the HomepagePresenter
        final HomepageOutputBoundary homepageOutputBoundary = new HomepagePresenter(
                viewManagerModel, homepageViewModel, settingsViewModel, chooseAvatarViewModel);
        final HomepageInputBoundary homepageInteractor = new HomepageInteractor(
                userDataAccessObject, homepageOutputBoundary);

        final HomepageController homepageController = new HomepageController(homepageInteractor);
        homepageView.setHomepageController(homepageController);
        return this;
    }

    /**
     * Adds the Choose Avatar Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChooseAvatarUseCase() {
        final ChooseAvatarOutputBoundary chooseAvatarOutputBoundary = new ChooseAvatarPresenter(chooseAvatarViewModel, viewManagerModel, inputNameViewModel);
        final ChooseAvatarInputBoundary chooseAvatarInteractor = new ChooseAvatarInteractor(userDataAccessObject, chooseAvatarOutputBoundary);
        final ChooseAvatarController chooseAvatarController = new ChooseAvatarController(chooseAvatarInteractor);
        chooseAvatarView.setController(chooseAvatarController);
        return this;
    }

    /**
     * Adds the Input Name Use Case to the application.
     * @return this builder
     */
    public AppBuilder addInputNameUseCase() {
        final InputNameOutputBoundary inputNameOutputBoundary = new InputNamePresenter(inputNameViewModel, viewManagerModel);
        final InputNameInputBoundary inputNameInteractor = new InputNameInteractor(userDataAccessObject, inputNameOutputBoundary);
        final InputNameController inputNameController = new InputNameController(inputNameInteractor);
        inputNameView.setController(inputNameController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(changePasswordViewModel, viewManagerModel, settingsViewModel);
        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);
        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        changePasswordView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel, settingsViewModel, loginViewModel);
        final LogoutInputBoundary logoutInteractor = new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);
        final LogoutController logoutController = new LogoutController(logoutInteractor);
        settingsView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Settings Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSettingsUseCase() {
        final SettingsOutputBoundary settingsOutputBoundary = new SettingsPresenter(settingsViewModel, viewManagerModel, changePasswordViewModel, homepageViewModel);
        final SettingsInputBoundary settingsInteractor = new SettingsInteractor(userDataAccessObject, settingsOutputBoundary);
        final SettingsController settingsController = new SettingsController(settingsInteractor);
        settingsView.setSettingsController(settingsController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();
        return application;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

}