package use_case.homepage;

import entity.User;
import use_case.login.*;

/**
 * The Homepage Interactor.
 */
public class HomepageInteractor implements HomepageInputBoundary  {

    private final HomepageOutputBoundary homepagePresenter;

    public HomepageInteractor(HomepageOutputBoundary homepageOutputBoundary) {
        this.homepagePresenter = homepageOutputBoundary;
    }

    @Override
    public void switchToCharacterView() { homepagePresenter.switchToCharacterView(); }

    @Override
    public void switchToGameView() { homepagePresenter.switchToGameView(); }

    @Override
    public void switchToDecisionView() { homepagePresenter.switchToDecisionView(); }

    @Override
    public void switchToProfileView() { homepagePresenter.switchToProfileView(); }
}
