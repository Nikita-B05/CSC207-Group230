package use_case.homepage;

import entity.User;

/**
 * The Homepage Interactor.
 */
public class HomepageInteractor implements HomepageInputBoundary  {

    private final HomepageUserDataAccessInterface userDataAccessObject;
    private final HomepageOutputBoundary homepagePresenter;

    public HomepageInteractor(
            HomepageUserDataAccessInterface homepageUserDataAccessInterface,
            HomepageOutputBoundary homepageOutputBoundary) {
        this.userDataAccessObject = homepageUserDataAccessInterface;
        this.homepagePresenter = homepageOutputBoundary;
    }

    @Override
    public void switchToChooseAvatarView() {
        final User user = userDataAccessObject.getCurrentUser();
        homepagePresenter.switchToChooseAvatarView(new HomepageOutputData(
                user.getUsername(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions()
        ));
    }

    @Override
    public void switchToPlayGameView() {
        final User user = userDataAccessObject.getCurrentUser();
        homepagePresenter.switchToPlayGameView(new HomepageOutputData(
                user.getUsername(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions()
        ));
    }

    @Override
    public void switchToDecisionLogView() {
        final User user = userDataAccessObject.getCurrentUser();
        homepagePresenter.switchToDecisionLogView(new HomepageOutputData(
                user.getUsername(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions()
        ));
    }

    @Override
    public void switchToProfileSettingsView() {
        final User user = userDataAccessObject.getCurrentUser();
        homepagePresenter.switchToSettingsView(new HomepageOutputData(
                user.getUsername(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions()
        ));
    }
}
