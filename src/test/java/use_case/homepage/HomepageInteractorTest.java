package use_case.homepage;

import data_access.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.login.*;

import static org.junit.jupiter.api.Assertions.*;

public class HomepageInteractorTest {

    @Test
    void switchToChooseAvatarViewTest() {
        HomepageInputData inputData = new HomepageInputData("Paul");

        HomepageOutputBoundary avatarPresenter = new HomepageOutputBoundary() {
            @Override
            public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
                assertEquals("Paul", homepageOutputData.getUsername());
            }

            @Override
            public void switchToPlayGameView(HomepageOutputData homepageOutputData) {
                fail("Use case PlayGameView is unexpected.");
            }

            @Override
            public void switchToDecisionLogView(HomepageOutputData homepageOutputData) {
                fail("Use case DecisionLogView is unexpected.");
            }

            @Override
            public void switchToProfileSettingsView(HomepageOutputData homepageOutputData) {
                fail("Use case ProfileSettingsView is unexpected.");
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(avatarPresenter);
        interactor.switchToChooseAvatarView(inputData);
    }

    @Test
    void switchToPlayGameViewTest() {
        HomepageInputData inputData = new HomepageInputData("Paul");

        HomepageOutputBoundary playGamePresenter = new HomepageOutputBoundary() {
            @Override
            public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
                fail("Use case ChooseAvatarView is unexpected.");
            }

            @Override
            public void switchToPlayGameView(HomepageOutputData homepageOutputData) {
                assertEquals("Paul", homepageOutputData.getUsername());
            }

            @Override
            public void switchToDecisionLogView(HomepageOutputData homepageOutputData) {
                fail("Use case DecisionLogView is unexpected.");
            }

            @Override
            public void switchToProfileSettingsView(HomepageOutputData homepageOutputData) {
                fail("Use case ProfileSettingsView is unexpected.");
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(playGamePresenter);
        interactor.switchToPlayGameView(inputData);
    }

    @Test
    void switchToDecisionLogViewTest() {
        HomepageInputData inputData = new HomepageInputData("Paul");

        HomepageOutputBoundary decisionLogPresenter = new HomepageOutputBoundary() {
            @Override
            public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
                fail("Use case ChooseAvatarView is unexpected.");
            }

            @Override
            public void switchToPlayGameView(HomepageOutputData homepageOutputData) {
                fail("Use case PlayGameView is unexpected.");
            }

            @Override
            public void switchToDecisionLogView(HomepageOutputData homepageOutputData) {
                assertEquals("Paul", homepageOutputData.getUsername());
            }

            @Override
            public void switchToProfileSettingsView(HomepageOutputData homepageOutputData) {
                fail("Use case ProfileSettingsView is unexpected.");
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(decisionLogPresenter);
        interactor.switchToDecisionLogView(inputData);
    }

    @Test
    void switchToProfileSettingsViewTest() {
        HomepageInputData inputData = new HomepageInputData("Paul");

        HomepageOutputBoundary settingsPresenter = new HomepageOutputBoundary() {
            @Override
            public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
                fail("Use case ChooseAvatarView is unexpected.");
            }

            @Override
            public void switchToPlayGameView(HomepageOutputData homepageOutputData) {
                fail("Use case PlayGameView is unexpected.");
            }

            @Override
            public void switchToDecisionLogView(HomepageOutputData homepageOutputData) {
                fail("Use case DecisionLogView is unexpected.");
            }

            @Override
            public void switchToProfileSettingsView(HomepageOutputData homepageOutputData) {
                assertEquals("Paul", homepageOutputData.getUsername());
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(settingsPresenter);
        interactor.switchToProfileSettingsView(inputData);
    }
}