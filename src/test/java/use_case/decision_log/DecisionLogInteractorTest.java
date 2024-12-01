package use_case.decision_log;

import entity.CommonUserFactory;
import entity.Decision;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.time.LocalDateTime;
import data_access.MongoDBUserDataAccessObject;
import use_case.homepage.HomepageOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class DecisionLogInteractorTest {

    @Test
    void successTest() {

        // Create decisions for the user
        Decision decision1 = new Decision(LocalDateTime.now(), "Decision text 1", "Option 1",
                -100.0, 10.0);
        Decision decision2 = new Decision(LocalDateTime.now(), "Decision text 2", "Option 2",
                50.0, -5.0);
        ArrayList<Decision> decisions = new ArrayList<>();
        decisions.add(decision1);
        decisions.add(decision2);

        // Create the user and assign the decisions
        String username = "Paul";
        MongoDBUserDataAccessObject decisionRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        UserFactory factory = new CommonUserFactory();
        User user = factory.create(username, "password");
        user.setDecisions(decisions);

        // Save the user to the repository (simulating a database save)
        decisionRepository.save(user);

        // Prepare the presenter for success
        DecisionLogOutputBoundary successPresenter = new DecisionLogOutputBoundary() {
            @Override
            public void prepareSuccessView(DecisionLogOutputData outputData) {
                assertEquals("Paul", outputData.getUsername());
                assertEquals(2, outputData.getDecisions().size());
                assertEquals(-100.0, outputData.getDecisions().get(0).getNetWorthChange());
            }

            @Override
            public void switchToHomepageView() {
                fail("Homepage view is not expected");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure should not happen in success test");
            }

            @Override
            public void switchToDecisionLogView(DecisionLogOutputData outputData) {
                fail("Decision log view is not expected");
            }
        };

        // Create the interactor
        DecisionLogInputBoundary interactor =
                new DecisionLogInteractor(decisionRepository, successPresenter, decisionRepository);

        // Create the input data
        DecisionLogInputData inputData = new DecisionLogInputData(username);

        // Execute the interactor and check results
        interactor.execute(inputData);
    }

    @Test
    void failureTest_noDecisions() {
        // Create the user but don't add any decisions
        String username = "Paul";
        MongoDBUserDataAccessObject decisionRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        UserFactory factory = new CommonUserFactory();
        User user = factory.create(username, "password");
        decisionRepository.save(user); // Save the user with no decisions

        // Prepare the presenter for failure
        DecisionLogOutputBoundary failurePresenter = new DecisionLogOutputBoundary() {
            @Override
            public void prepareSuccessView(DecisionLogOutputData outputData) {
                fail("Success should not happen when there are no decisions");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No decisions found for Paul", error);
            }

            @Override
            public void switchToHomePageView() {
                // Handle expected behavior for switching
            }
        };

        // Create the interactor
        DecisionLogInputBoundary interactor =
                new DecisionLogInteractor(decisionRepository, failurePresenter, decisionRepository);

        // Create the input data
        DecisionLogInputData inputData = new DecisionLogInputData(username);

        // Execute the interactor and check results
        interactor.execute(inputData);
    }
}
