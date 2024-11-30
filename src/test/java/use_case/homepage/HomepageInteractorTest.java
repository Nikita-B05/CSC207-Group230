package use_case.homepage;

import data_access.DBUserDataAccessObject;
import data_access.MongoDBUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import use_case.login.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HomepageInteractorTest {

    @AfterEach
    public void tearDown() {
        MongoDBUserDataAccessObject userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.deleteUser("testing");
    }

    @Test
    void switchToChooseAvatarViewTest() {
        User user = new CommonUser("Paul", "password");
        MongoDBUserDataAccessObject userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.setCurrentUsername("Paul");
        userRepository.save(user);

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
        interactor.switchToChooseAvatarView();
    }

    @Test
    void switchToPlayGameViewTest() {
        User user = new CommonUser("Paul", "password");
        MongoDBUserDataAccessObject userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.setCurrentUsername("Paul");
        userRepository.save(user);

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
        interactor.switchToPlayGameView();
    }

    @Test
    void switchToDecisionLogViewTest() {
        User user = new CommonUser("testing", "password");
        MongoDBUserDataAccessObject userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.setCurrentUsername("testing");
        userRepository.save(user);

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
                assertEquals("testing", homepageOutputData.getUsername());
                assertEquals(new Avatar().getId(), homepageOutputData.getAvatar().getId());
                assertEquals(new ArrayList<>(), homepageOutputData.getDecisions());
            }

            @Override
            public void switchToSettingsView(HomepageOutputData homepageOutputData) {
                fail("Use case ProfileSettingsView is unexpected.");
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, decisionLogPresenter);
        interactor.switchToDecisionLogView();
    }

    @Test
    void switchToProfileSettingsViewTest() {
        User user = new CommonUser("testing", "password");
        MongoDBUserDataAccessObject userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.setCurrentUsername("testing");
        userRepository.save(user);

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
                assertEquals("testing", homepageOutputData.getUsername());
                assertEquals(new Avatar().getId(), homepageOutputData.getAvatar().getId());
                assertEquals(new ArrayList<>(), homepageOutputData.getDecisions());
            }
        };

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, settingsPresenter);
        interactor.switchToProfileSettingsView();
    }
}