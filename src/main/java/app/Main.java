package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */

public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addHomepageView()
                .addSettingsView()
                .addChangePasswordView()
                .addAssetManagerView()
                .addManageHomeView()
                .addManageStockView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addSettingsUseCase()
                .addChangePasswordUseCase()
                .addHomepageUseCase()
                .addChooseAssetUseCase()
                .addManageHomeUseCase()
                .addManageStockUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
