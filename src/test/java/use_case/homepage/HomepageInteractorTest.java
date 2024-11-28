package use_case.homepage;

import data_access.DBUserDataAccessObject;
import entity.Avatar;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.login.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HomepageInteractorTest {

    @Test
    void switchToChooseAvatarViewTest() {
        HomepageInputData inputData = new HomepageInputData("Paul");
        HomepageUserDataAccessInterface userRepository = new DBUserDataAccessObject(new CommonUserFactory());

        HomepageOutputBoundary avatarPresenter = new HomepageOutputBoundary() {
            @Override
            public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
                assertEquals("Paul", homepageOutputData.getUsername());
                assertEquals(new Avatar().getId(), homepageOutputData.getAvatar().getId());
                assertEquals(new ArrayList<>(), homepageOutputData.getDecisions());
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
            public void switchToSettingsView(HomepageOutputData homepageOutputData) {
                fail("Use case ProfileSettingsView is unexpected.");
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, avatarPresenter);
        interactor.switchToChooseAvatarView(inputData);
    }

    @Test
    void switchToPlayGameViewTest() {
        HomepageInputData inputData = new HomepageInputData("Paul");
        HomepageUserDataAccessInterface userRepository = new DBUserDataAccessObject(new CommonUserFactory());

        HomepageOutputBoundary playGamePresenter = new HomepageOutputBoundary() {
            @Override
            public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
                fail("Use case ChooseAvatarView is unexpected.");
            }

            @Override
            public void switchToPlayGameView(HomepageOutputData homepageOutputData) {
                assertEquals("Paul", homepageOutputData.getUsername());
                assertEquals(new Avatar().getId(), homepageOutputData.getAvatar().getId());
                assertEquals(new ArrayList<>(), homepageOutputData.getDecisions());
            }

            @Override
            public void switchToDecisionLogView(HomepageOutputData homepageOutputData) {
                fail("Use case DecisionLogView is unexpected.");
            }

            @Override
            public void switchToSettingsView(HomepageOutputData homepageOutputData) {
                fail("Use case ProfileSettingsView is unexpected.");
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, playGamePresenter);
        interactor.switchToPlayGameView(inputData);
    }

    @Test
    void switchToDecisionLogViewTest() {
        HomepageInputData inputData = new HomepageInputData("Paul");
        HomepageUserDataAccessInterface userRepository = new DBUserDataAccessObject(new CommonUserFactory());

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
                assertEquals(new Avatar().getId(), homepageOutputData.getAvatar().getId());
                assertEquals(new ArrayList<>(), homepageOutputData.getDecisions());
            }

            @Override
            public void switchToSettingsView(HomepageOutputData homepageOutputData) {
                fail("Use case ProfileSettingsView is unexpected.");
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, decisionLogPresenter);
        interactor.switchToDecisionLogView(inputData);
    }

    @Test
    void switchToProfileSettingsViewTest() {
        HomepageInputData inputData = new HomepageInputData("Paul");
        HomepageUserDataAccessInterface userRepository = new DBUserDataAccessObject(new CommonUserFactory());

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
            public void switchToSettingsView(HomepageOutputData homepageOutputData) {
                assertEquals("Paul", homepageOutputData.getUsername());
                assertEquals(new Avatar().getId(), homepageOutputData.getAvatar().getId());
                assertEquals(new ArrayList<>(), homepageOutputData.getDecisions());
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, settingsPresenter);
        interactor.switchToProfileSettingsView(inputData);
    }
}