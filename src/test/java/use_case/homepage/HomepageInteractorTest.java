package use_case.homepage;

import data_access.MongoDBUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stock_api.PolygonStockDataAccessObject;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HomepageInteractorTest {
    private static MongoDBUserDataAccessObject userRepository;
    private static PolygonStockDataAccessObject stockRepository;

    @BeforeAll
    public static void setUp() {
        userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        User user = new CommonUser("testing", "password");
        userRepository.setCurrentUsername(user.getUsername());
        userRepository.save(user);
        stockRepository = new PolygonStockDataAccessObject();
    }

    @AfterAll
    public static void tearDown() {
        userRepository.deleteUser("testing");
    }

    @Test
    void switchToChooseAvatarViewTest() {
        HomepageOutputBoundary avatarPresenter = new HomepageOutputBoundary() {
            @Override
            public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
                assertEquals("testing", homepageOutputData.getUsername());
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

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, avatarPresenter, stockRepository);
        interactor.switchToChooseAvatarView(new HomepageInputData("testing"));
    }

    @Test
    void switchToPlayGameViewTest() {
        HomepageOutputBoundary playGamePresenter = new HomepageOutputBoundary() {
            @Override
            public void switchToChooseAvatarView(HomepageOutputData homepageOutputData) {
                fail("Use case ChooseAvatarView is unexpected.");
            }

            @Override
            public void switchToPlayGameView(HomepageOutputData homepageOutputData) {
                assertEquals("testing", homepageOutputData.getUsername());
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

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, playGamePresenter, stockRepository);
        interactor.switchToPlayGameView(new HomepageInputData("testing"));
    }

    @Test
    void switchToDecisionLogViewTest() {
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

        HomepageInputBoundary interactor = new HomepageInteractor(
                userRepository, decisionLogPresenter, stockRepository);
        interactor.switchToDecisionLogView(new HomepageInputData("testing"));
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

        HomepageInputBoundary interactor = new HomepageInteractor(userRepository, settingsPresenter, stockRepository);
        interactor.switchToProfileSettingsView(new HomepageInputData("testing"));
    }
}
