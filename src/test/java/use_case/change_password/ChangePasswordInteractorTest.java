package use_case.change_password;

import data_access.MongoDBUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePasswordInteractorTest {
    private static MongoDBUserDataAccessObject userRepository;

    @BeforeAll
    public static void setUp() {
        User user = new CommonUser("testing", "password");
        userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.setCurrentUsername("testing");
        userRepository.save(user);
    }

    @AfterAll
    public static void tearDown() {
        userRepository.deleteUser("testing");
    }

    @Test
    void successTest() {
        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is unexpected.");
            }

            @Override
            public void switchToSettingsView() {
                fail("Use case is unexpected.");
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                userRepository, successPresenter, new CommonUserFactory());
        ChangePasswordInputData inputData = new ChangePasswordInputData("testing", "new password");
        interactor.execute(inputData);
        userRepository.deleteUser("testing");
        userRepository.save(new CommonUser("testing", "password"));
        userRepository.setCurrentUsername("testing");
    }

    @Test
    void failurePasswordIsEmptyTest() {
        ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                fail("Use case is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Password cannot be empty.", errorMessage);
            }

            @Override
            public void switchToSettingsView() {
                fail("Use case is unexpected.");
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                userRepository, failurePresenter, new CommonUserFactory());
        ChangePasswordInputData inputData = new ChangePasswordInputData("testing", "");
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordTooShortTest() {
        ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                fail("Use case is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Password must be at least 8 characters.", errorMessage);
            }

            @Override
            public void switchToSettingsView() {
                fail("Use case is unexpected.");
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                userRepository, failurePresenter, new CommonUserFactory());
        ChangePasswordInputData inputData = new ChangePasswordInputData("testing", "1234");
        interactor.execute(inputData);
    }

    @Test
    void switchToSettingsViewTest() {
        ChangePasswordOutputBoundary settingsPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                fail("Use case is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is unexpected.");
            }

            @Override
            public void switchToSettingsView() {
                assertTrue(true);
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                userRepository, settingsPresenter, new CommonUserFactory());
        interactor.switchToSettingsView();
    }
}
