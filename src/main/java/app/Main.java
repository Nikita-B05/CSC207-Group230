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
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addSettingsUseCase()
                .addChangePasswordUseCase()
                .addHomepageUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
