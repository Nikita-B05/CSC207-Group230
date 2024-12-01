package use_case.decision_log;

import entity.CommonUserFactory;
import entity.Decision;
import data_access.MongoDBUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInteractor;
import use_case.login.LoginUserDataAccessInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DecisionLogInteractor focusing on interactions without mocks.
 */
class DecisionLogInteractorTest {
    @Test
    void successTest() {
        // Arrange: Set up the test data
        String username = "Paul";
        List<Decision> decisions = new ArrayList<>();
        Decision decision1 = new Decision(
                LocalDateTime.now(),
                "You are an hour walk away from school, and walking drains you. " +
                        "Would you like to get a Maserati, used 2001 car, bicycle, or just walk?",
                "get a Maserati",
                -101010,  // Example net worth change
                100        // Example happiness change
        );
        Decision decision2 = new Decision(
                LocalDateTime.now(),
                "Your car window broke, would you like to pay to replace it or " +
                        "just duct tape it up for now?",
                "duct tape",
                -1,       // Example net worth change
                -10       // Example happiness change
        );
        decisions.add(decision1);
        decisions.add(decision2);

        // Add decisions to the MongoDBUserDataAccessObject repository
        MongoDBUserDataAccessObject userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());

        // Create successPresenter that checks if success logic is handled correctly
        DecisionLogOutputBoundary successPresenter = new DecisionLogOutputBoundary() {
            @Override
            public void prepareSuccessView(DecisionLogOutputData outputData) {
                assertNotNull(outputData);
                assertEquals(2, outputData.getDecisions().size());
                assertEquals("get a Maserati", outputData.getDecisions().get(0).
                        getDecisionText());
                assertEquals("duct tape", outputData.getDecisions().get(1).
                        getDecisionText());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure view should not be called in success test.");
            }

            @Override
            public void switchToDecisionLogView(DecisionLogOutputData outputData) {
                // Assert that Decision Log view is being called with expected data
                assertNotNull(outputData);
                assertEquals("Paul", outputData.getUsername());  // Example username check
            }

            @Override
            public void switchToHomePageView() {
                fail("Use case HomepageView is unexpected.");
            }
        };

        // Create interactor and execute
        DecisionLogInputBoundary interactor = new DecisionLogInteractor(userRepository, successPresenter);
        DecisionLogInputData inputData = new DecisionLogInputData(username);
        interactor.execute(inputData);
    }

    @Test
    void failureNoDecisionsTest() {
        // Arrange: Set up the test data (no decisions for this user)
        String username = "Paul";

        // Create an empty repository (simulate no decisions for the user)
        DecisionLogUserDataAccessInterface decisionRepository = new DecisionLogUserDataAccessInterface() {

            @Override
            public String loadDecisionLog(User user) {
                return "";
            }

            @Override
            public List<Decision> getDecisions(String username) {
                return List.of();
            }

            @Override
            public List<Decision> getCurrentUsername(String username) {
                return List.of();
            }
        };

        // Create the presenter that checks if failure is handled correctly
        DecisionLogOutputBoundary failurePresenter = new DecisionLogOutputBoundary() {
            @Override
            public void prepareSuccessView(DecisionLogOutputData outputData) {
                fail("Success view should not be called in failure test.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No decisions found for Paul", error);
            }

            @Override
            public void switchToDecisionLogView(DecisionLogOutputData outputData) {
                // Assert that Decision Log view is being called with expected data
                assertNotNull(outputData);
                assertEquals("Paul", outputData.getUsername());  // Example username check
                }

            boolean wasHomePageViewCalled = false;
            @Override
            public void switchToHomePageView() {
                // Assert that navigation to the home page view is triggered
                wasHomePageViewCalled = true;
            }
        };

        // Create interactor and execute
        DecisionLogInputBoundary interactor =
                new DecisionLogInteractor(decisionRepository, failurePresenter);
        DecisionLogInputData inputData = new DecisionLogInputData(username);
        interactor.execute(inputData);
    }

    @Test
    void switchToHomepageTest() {
        // Arrange: Set up the repository and presenter
        LoginUserDataAccessInterface userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        DecisionLogOutputBoundary presenter = new DecisionLogOutputBoundary() {
            @Override
            public void prepareSuccessView(DecisionLogOutputData outputData) {
                // No success view needed for this test
            }

            @Override
            public void prepareFailView(String error) {
                // No failure view needed for this test
            }

            @Override
            public void switchToDecisionLogView(DecisionLogOutputData outputData) {

            }

            @Override
            public void switchToHomePageView() {
                // Navigation logic test
                System.out.println("Switched to Homepage View");
            }
        };

        // Create interactor and execute switch to homepage logic
        DecisionLogInputBoundary interactor = new DecisionLogInteractor(repository, presenter);
        interactor.switchToHomepageView();
    }
}