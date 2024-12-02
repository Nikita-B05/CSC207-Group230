package use_case.homepage;

import entity.User;

/**
 * The Homepage Interactor.
 */
public class HomepageInteractor implements HomepageInputBoundary  {

    private final HomepageUserDataAccessInterface userDataAccessObject;
    private final HomepageOutputBoundary homepagePresenter;
    private final HomepageStockAccessInterface stockAccessObject;

    public HomepageInteractor(
            HomepageUserDataAccessInterface homepageUserDataAccessInterface,
            HomepageOutputBoundary homepageOutputBoundary,
            HomepageStockAccessInterface homepageStockAccessInterface) {
        this.userDataAccessObject = homepageUserDataAccessInterface;
        this.homepagePresenter = homepageOutputBoundary;
        this.stockAccessObject = homepageStockAccessInterface;
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
                user.getSalary(),
                user.getAssets()
        ));
    }

    @Override
    public void switchToPlayGameView(HomepageInputData homepageInputData) {
        final String username = homepageInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        final HomepageOutputData outputData = new HomepageOutputData(
                username,
                user.getCharacterName(),
                user.getAvatar(),
                user.isDarkMode(),
                user.getDecisions(),
                user.getAge(),
                user.getQuestion().get(user.getAge()),
                user.getHappiness(),
                user.getSalary(),
                user.getAssets()
        );
        outputData.setStockPrices(stockAccessObject.getStockPrices());
        homepagePresenter.switchToPlayGameView(outputData);
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
                user.getSalary(),
                user.getAssets()
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
                user.getSalary(),
                user.getAssets()
        ));
    }
}
