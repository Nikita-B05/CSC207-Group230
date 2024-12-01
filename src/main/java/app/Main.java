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
                .addSignupView()
                .addLoginView()
                .addHomepageView()
                .addChooseAvatarView()
                .addInputNameView()
                .addChangePasswordView()
                .addSettingsView()
                .addDecisionLogView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addHomepageUseCase()
                .addChooseAvatarUseCase()
                .addInputNameUseCase()
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .addSettingsUseCase()
                .addDecisionLogUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
