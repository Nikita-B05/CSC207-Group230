package app;

import java.awt.CardLayout;
import javax.swing.*;

import data_access.MongoDBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.asset_manager.AssetManagerController;
import interface_adapter.asset_manager.AssetManagerPresenter;
import interface_adapter.asset_manager.AssetManagerViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.choose_avatar.ChooseAvatarController;
import interface_adapter.choose_avatar.ChooseAvatarPresenter;
import interface_adapter.choose_avatar.ChooseAvatarViewModel;
import interface_adapter.dark_mode.DarkModeController;
import interface_adapter.game_decision.GameDecisionController;
import interface_adapter.game_decision.GameDecisionPresenter;
import interface_adapter.game_decision.GameDecisionViewModel;
import interface_adapter.game_over.GameOverController;
import interface_adapter.game_over.GameOverPresenter;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.decision_log.DecisionLogPresenter;
import interface_adapter.decision_log.DecisionLogViewModel;
import interface_adapter.decision_log.DecisionLogController;
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
import interface_adapter.manage_home.ManageHomeController;
import interface_adapter.manage_home.ManageHomePresenter;
import interface_adapter.manage_home.ManageHomeViewModel;
import interface_adapter.manage_stock.ManageStockController;
import interface_adapter.manage_stock.ManageStockPresenter;
import interface_adapter.manage_stock.ManageStockViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.settings.SettingsController;
import interface_adapter.settings.SettingsPresenter;
import interface_adapter.settings.SettingsViewModel;
import stock_api.PolygonStockDataAccessObject;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.choose_avatar.ChooseAvatarInputBoundary;
import use_case.choose_avatar.ChooseAvatarInteractor;
import use_case.choose_avatar.ChooseAvatarOutputBoundary;
import use_case.choose_asset.ChooseAssetInputBoundary;
import use_case.choose_asset.ChooseAssetInteractor;
import use_case.choose_asset.ChooseAssetOutputBoundary;
import use_case.game_decision.GameDecisionInputBoundary;
import use_case.game_decision.GameDecisionInteractor;
import use_case.game_decision.GameDecisionOutputBoundary;
import use_case.game_over.GameOverInputBoundary;
import use_case.game_over.GameOverInteractor;
import use_case.game_over.GameOverOutputBoundary;
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
import use_case.manage_home.ManageHomeInputBoundary;
import use_case.manage_home.ManageHomeInteractor;
import use_case.manage_home.ManageHomeOutputBoundary;
import use_case.manage_stock.ManageStockInputBoundary;
import use_case.manage_stock.ManageStockInteractor;
import use_case.manage_stock.ManageStockOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.settings.SettingsInputBoundary;
import use_case.settings.SettingsInteractor;
import use_case.settings.SettingsOutputBoundary;
import use_case.decision_log.DecisionLogInputBoundary;
import use_case.decision_log.DecisionLogInteractor;
import use_case.decision_log.DecisionLogOutputBoundary;
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

    private final MongoDBUserDataAccessObject userDataAccessObject =
            new MongoDBUserDataAccessObject(new CommonUserFactory());
    private final PolygonStockDataAccessObject stockDataAccessObject = new PolygonStockDataAccessObject();

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
    private AssetManagerViewModel assetManagerViewModel;
    private AssetManagerView assetManagerView;
    private ManageHomeViewModel manageHomeViewModel;
    private ManageHomeView manageHomeView;
    private ManageStockViewModel manageStockViewModel;
    private ManageStockView manageStockView;
    private DecisionLogViewModel decisionLogViewModel;
    private DecisionLogView decisionLogView;

    // New Views and ViewModels for Choose Avatar and Input Name
    private ChooseAvatarViewModel chooseAvatarViewModel;
    private ChooseAvatarView chooseAvatarView;
    private InputNameViewModel inputNameViewModel;
    private InputNameView inputNameView;

    private GameDecisionViewModel gameDecisionViewModel;
    private GameDecisionView gameDecisionView;

    private GameOverViewModel gameOverViewModel;
    private GameOverView gameOverView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
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

    public AppBuilder addGameOverView() {
        gameOverViewModel = new GameOverViewModel();
        gameOverView = new GameOverView(gameOverViewModel);
        cardPanel.add(gameOverView, gameOverView.getViewName());
        return this;
    }

    /**
     * Adds the Game Decision View to the application.
     * @return this builder
     */
    public AppBuilder addGameDecisionView() {
        gameDecisionViewModel = new GameDecisionViewModel();
        gameDecisionView = new GameDecisionView(gameDecisionViewModel);
        cardPanel.add(gameDecisionView, gameDecisionView.getViewName());
        return this;
    }

    /**
     * Adds the Decision Log Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDecisionLogView() {
        decisionLogViewModel = new DecisionLogViewModel();
        decisionLogView = new DecisionLogView(decisionLogViewModel);
        cardPanel.add(decisionLogView, decisionLogView.getViewName());
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
        LogoutController logoutController =
                new LogoutController(new LogoutInteractor(userDataAccessObject,
                        new LogoutPresenter(viewManagerModel, settingsViewModel, loginViewModel)));
        settingsView = new SettingsView(settingsViewModel, darkModeController, logoutController);
        cardPanel.add(settingsView, settingsView.getViewName());

        return this;
    }

    /**
     * Adds the Asset Manager View to the application.
     * @return this builder
     */
    public AppBuilder addAssetManagerView() {
        assetManagerViewModel = new AssetManagerViewModel();
        assetManagerView = new AssetManagerView(assetManagerViewModel);
        cardPanel.add(assetManagerView, assetManagerView.getViewName());
        return this;
    }

    /**
     * Adds the Manager Home View to the application.
     * @return this builder
     */
    public AppBuilder addManageHomeView() {
        manageHomeViewModel = new ManageHomeViewModel();
        manageHomeView = new ManageHomeView(manageHomeViewModel);
        cardPanel.add(manageHomeView, manageHomeView.getViewName());
        return this;
    }

    /**
     * Adds the Manage Stock View to the application.
     * @return this builder
     */
    public AppBuilder addManageStockView() {
        manageStockViewModel = new ManageStockViewModel();
        manageStockView = new ManageStockView(manageStockViewModel);
        cardPanel.add(manageStockView, manageStockView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary =
                new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor =
                new SignupInteractor(userDataAccessObject, signupOutputBoundary, userFactory);
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
                viewManagerModel, homepageViewModel, settingsViewModel, chooseAvatarViewModel,
                gameDecisionViewModel, decisionLogViewModel);
        final HomepageInputBoundary homepageInteractor = new HomepageInteractor(
                userDataAccessObject, homepageOutputBoundary, stockDataAccessObject);

        final HomepageController homepageController = new HomepageController(homepageInteractor);
        homepageView.setHomepageController(homepageController);
        return this;
    }

    /**
     * Adds the Choose Avatar Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChooseAvatarUseCase() {
        final ChooseAvatarOutputBoundary chooseAvatarOutputBoundary =
                new ChooseAvatarPresenter(chooseAvatarViewModel, viewManagerModel, inputNameViewModel);
        final ChooseAvatarInputBoundary chooseAvatarInteractor =
                new ChooseAvatarInteractor(userDataAccessObject, chooseAvatarOutputBoundary);
        final ChooseAvatarController chooseAvatarController = new ChooseAvatarController(chooseAvatarInteractor);
        chooseAvatarView.setController(chooseAvatarController);
        return this;
    }

    /**
     * Adds the Input Name Use Case to the application.
     * @return this builder
     */
    public AppBuilder addInputNameUseCase() {
        final InputNameOutputBoundary inputNameOutputBoundary =
                new InputNamePresenter(inputNameViewModel, viewManagerModel, homepageViewModel);
        final InputNameInputBoundary inputNameInteractor =
                new InputNameInteractor(userDataAccessObject, inputNameOutputBoundary);
        final InputNameController inputNameController = new InputNameController(inputNameInteractor);
        inputNameView.setController(inputNameController);
        return this;
    }

    /**
     * Adds the Game Decision Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameDecisionUseCase() {
        final GameDecisionOutputBoundary gameDecisionOutputBoundary =
                new GameDecisionPresenter(
                        gameDecisionViewModel,
                        viewManagerModel,
                        homepageViewModel,
                        assetManagerViewModel,
                        gameOverViewModel
                );
        final GameDecisionInputBoundary gameDecisionInteractor =
                new GameDecisionInteractor(userDataAccessObject, gameDecisionOutputBoundary);
        final GameDecisionController gameDecisionController =
                new GameDecisionController(gameDecisionInteractor);
        gameDecisionView.setController(gameDecisionController);
        return this;
    }

    /**
     * Adds the Game Over Use Case to the application.
     * @return this builder
     */

    public AppBuilder addGameOverUseCase() {
        final GameOverOutputBoundary gameOverOutputBoundary =
                new GameOverPresenter(gameOverViewModel, viewManagerModel, homepageViewModel);
        final GameOverInputBoundary gameOverInteractor =
                new GameOverInteractor(userDataAccessObject, gameOverOutputBoundary);
        final GameOverController gameOverController =
                new GameOverController(gameOverInteractor);
        gameOverView.setController(gameOverController);
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
        final LogoutOutputBoundary logoutOutputBoundary =
                new LogoutPresenter(viewManagerModel, settingsViewModel, loginViewModel);
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
        final SettingsOutputBoundary settingsOutputBoundary =
                new SettingsPresenter(settingsViewModel, viewManagerModel, changePasswordViewModel,
                        homepageViewModel, loginViewModel);
        final SettingsInputBoundary settingsInteractor =
                new SettingsInteractor(userDataAccessObject, settingsOutputBoundary);
        final SettingsController settingsController = new SettingsController(settingsInteractor);
        settingsView.setSettingsController(settingsController);
        return this;
    }

    /**
     * Adds the Choose Asset Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChooseAssetUseCase() {
        final ChooseAssetOutputBoundary chooseAssetOutputBoundary = new AssetManagerPresenter(
                assetManagerViewModel,
                viewManagerModel,
                manageHomeViewModel,
                manageStockViewModel,
                gameDecisionViewModel
        );
        final ChooseAssetInputBoundary chooseAssetInteractor = new ChooseAssetInteractor(
                userDataAccessObject, chooseAssetOutputBoundary, stockDataAccessObject);
        final AssetManagerController assetManagerController = new AssetManagerController(chooseAssetInteractor);
        assetManagerView.setAssetManagerController(assetManagerController);
        return this;
    }

    public AppBuilder addManageHomeUseCase() {
        final ManageHomeOutputBoundary manageHomeOutputBoundary = new ManageHomePresenter(
                viewManagerModel, manageHomeViewModel, assetManagerViewModel);
        final ManageHomeInputBoundary manageHomeInteractor = new ManageHomeInteractor(
                userDataAccessObject, manageHomeOutputBoundary);
        final ManageHomeController manageHomeController = new ManageHomeController(manageHomeInteractor);
        manageHomeView.setManageHomeController(manageHomeController);
        return this;
    }

    public AppBuilder addManageStockUseCase() {
        final ManageStockOutputBoundary manageStockOutputBoundary = new ManageStockPresenter(
                viewManagerModel, manageStockViewModel, assetManagerViewModel);
        final ManageStockInputBoundary manageStockInteractor = new ManageStockInteractor(
                userDataAccessObject, stockDataAccessObject, manageStockOutputBoundary);
        final ManageStockController manageStockController = new ManageStockController(manageStockInteractor);
        manageStockView.setManageStockController(manageStockController);
        return this;
    }

    /**
     * Adds the Decision Log Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDecisionLogUseCase() {
        final DecisionLogOutputBoundary decisionLogOutputBoundary = new DecisionLogPresenter(
                decisionLogViewModel, viewManagerModel, homepageViewModel);
        final DecisionLogInputBoundary decisionLogInteractor = new DecisionLogInteractor(
                userDataAccessObject, decisionLogOutputBoundary);
        final DecisionLogController decisionLogController = new DecisionLogController(decisionLogInteractor);
        decisionLogView.setDecisionLogController(decisionLogController);
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
