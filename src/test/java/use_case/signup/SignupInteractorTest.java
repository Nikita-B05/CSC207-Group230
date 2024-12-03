package use_case.signup;

import data_access.MongoDBUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {
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
        userRepository.deleteUser("testing");
        SignupInputData inputData = new SignupInputData(
                "testing", "password", "password");
        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("testing", user.getUsername());
                assertTrue(userRepository.existsByName("testing"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                fail("Use case success is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(
                userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
        userRepository.save(new CommonUser("testing", "password"));
        userRepository.setCurrentUsername("testing");
    }

    @Test
    void failureUsernameIsEmptyTest() {
        userRepository.deleteUser("testing");
        SignupInputData inputData = new SignupInputData(
                "", "password", "password");

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username cannot be empty.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case success is unexpected.");
            }
        };

        SignupInputBoundary interactor =
                new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
        userRepository.save(new CommonUser("testing", "password"));
        userRepository.setCurrentUsername("testing");
    }

    @Test
    void failurePasswordIsEmptyTest() {
        userRepository.deleteUser("testing");
        SignupInputData inputData = new SignupInputData(
                "testing", "", "password");

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password cannot be empty.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case success is unexpected.");
            }
        };

        SignupInputBoundary interactor =
                new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
        userRepository.save(new CommonUser("testing", "password"));
        userRepository.setCurrentUsername("testing");
    }

    @Test
    void failurePasswordTooShortTest() {
        userRepository.deleteUser("testing");
        SignupInputData inputData = new SignupInputData(
                "testing", "1234", "1234");

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password must be at least 8 characters.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case success is unexpected.");
            }
        };

        SignupInputBoundary interactor =
                new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
        userRepository.save(new CommonUser("testing", "password"));
        userRepository.setCurrentUsername("testing");
    }

    @Test
    void failurePasswordMismatchTest() {
        userRepository.deleteUser("testing");
        SignupInputData inputData = new SignupInputData(
                "testing", "password", "wrong");

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case success is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
        userRepository.save(new CommonUser("testing", "password"));
        userRepository.setCurrentUsername("testing");
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData(
                "testing", "password", "wrong");

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case success is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(
                userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
        userRepository.save(new CommonUser("testing", "password"));
        userRepository.setCurrentUsername("testing");
    }

    @Test
    void switchToLoginViewTest() {
        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(
                userRepository, failurePresenter, new CommonUserFactory());
        interactor.switchToLoginView();
    }
}
