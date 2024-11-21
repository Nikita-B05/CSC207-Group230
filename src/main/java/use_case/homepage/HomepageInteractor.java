//package use_case.homepage;
//
//import entity.User;
//import use_case.login.*;
//
///**
// * The Homepage Interactor.
// */
//public class HomepageInteractor implements HomepageInputBoundary  {
//
//    private final HomepageOutputBoundary homepagePresenter;
//
//    public HomepageInteractor(HomepageOutputBoundary homepageOutputBoundary) {
//        this.homepagePresenter = homepageOutputBoundary;
//    }
//
//    @Override
//    public void switchToAvatarView(HomepageInputData homepageInputData) {
//        homepagePresenter.switchToAvatarView(new HomepageOutputData(homepageInputData.getUsername()));
//    }
//
//    @Override
//    public void switchToPlayGameView(HomepageInputData homepageInputData) {
//        homepagePresenter.switchToPlayGameView(new HomepageOutputData(homepageInputData.getUsername()));
//    }
//
//    @Override
//    public void switchToDecisionLogView(HomepageInputData homepageInputData) {
//        homepagePresenter.switchToDecisionLogView(new HomepageOutputData(homepageInputData.getUsername()));
//    }
//
//    @Override
//    public void switchToProfileSettingsView(HomepageInputData homepageInputData) {
//        homepagePresenter.switchToProfileSettingsView(new HomepageOutputData(homepageInputData.getUsername()));
//    }
//}
