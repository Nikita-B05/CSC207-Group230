package use_case.settings;

import data_access.MongoDBUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SettingsInteractorTest {
    private static MongoDBUserDataAccessObject userRepository;

    @BeforeAll
    public static void setUp() {
        // Create and save the test user
        User user = new CommonUser("testing", "password");
        userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.save(user);

        // Set the current username and verify it's set
        userRepository.setCurrentUsername("testing");
        assertNotNull(userRepository.getCurrentUser(), "Current user should not be null after setup.");
    }

    @AfterAll
    public static void tearDown() {
        userRepository.deleteUser("testing");
    }

    @Test
    void navigateToChangePasswordTest() {
        // Ensure the user is logged in
        userRepository.setCurrentUsername("testing");

        SettingsOutputBoundary passwordPresenter = new SettingsOutputBoundary() {
            @Override
            public void prepareChangePasswordView(SettingsOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }

            @Override
            public void prepareLogoutView(SettingsOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareHomepageView(SettingsOutputData outputData) {
                fail("Use case is unexpected");
            }
        };

        SettingsInputBoundary interactor = new SettingsInteractor(userRepository, passwordPresenter);
        interactor.navigateToChangePassword(new SettingsInputData("testing", true));
    }

    @Test
    void logoutTest() {
        userRepository.setCurrentUsername("testing");

        SettingsOutputBoundary logoutPresenter = new SettingsOutputBoundary() {
            @Override
            public void prepareChangePasswordView(SettingsOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareLogoutView(SettingsOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }

            @Override
            public void prepareHomepageView(SettingsOutputData outputData) {
                fail("Use case is unexpected");
            }
        };

        SettingsInputBoundary interactor = new SettingsInteractor(userRepository, logoutPresenter);

        interactor.logout(new SettingsInputData("testing", true));

        assertThrows(IllegalStateException.class, userRepository::getCurrentUser);
    }

    @Test
    void navigateToHomePageTest() {
        // Ensure the user is logged in
        userRepository.setCurrentUsername("testing");

        SettingsOutputBoundary homepagePresenter = new SettingsOutputBoundary() {
            @Override
            public void prepareChangePasswordView(SettingsOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareLogoutView(SettingsOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareHomepageView(SettingsOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }
        };

        SettingsInputBoundary interactor = new SettingsInteractor(userRepository, homepagePresenter);
        interactor.navigateToHomePage(new SettingsInputData("testing", true));
    }
}
