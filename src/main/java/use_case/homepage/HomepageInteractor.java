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
    public void switchToChooseAvatarView(HomepageInputData homepageInputData) {
        final String username = homepageInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        homepagePresenter.switchToChooseAvatarView(new HomepageOutputData(
                username,
                user.getCharacterName(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions(),
                user.getAge(),
                user.getQuestion().get(user.getAge()),
                user.getHappiness(),
                user.getSalary()
        ));
    }

    @Override
    public void switchToPlayGameView(HomepageInputData homepageInputData) {
        final String username = homepageInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        homepagePresenter.switchToPlayGameView(new HomepageOutputData(
                username,
                user.getCharacterName(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions(),
                user.getAge(),
                user.getQuestion().get(user.getAge()),
                user.getHappiness(),
                user.getSalary()
        ));
    }

    @Override
    public void switchToDecisionLogView(HomepageInputData homepageInputData) {
        final String username = homepageInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        homepagePresenter.switchToDecisionLogView(new HomepageOutputData(
                username,
                user.getCharacterName(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions(),
                user.getAge(),
                user.getQuestion().get(user.getAge()),
                user.getHappiness(),
                user.getSalary()
        ));
    }

    @Override
    public void switchToProfileSettingsView(HomepageInputData homepageInputData) {
        final String username = homepageInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        homepagePresenter.switchToSettingsView(new HomepageOutputData(
                username,
                user.getCharacterName(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions(),
                user.getAge(),
                user.getQuestion().get(user.getAge()),
                user.getHappiness(),
                user.getSalary()
        ));
    }
}
