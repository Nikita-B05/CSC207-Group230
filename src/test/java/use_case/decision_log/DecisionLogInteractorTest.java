package use_case.decision_log;

import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DecisionLogInteractorTest {
    private TestDecisionLogUserDataAccess userDataAccessObject;
    private TestDecisionLogOutputBoundary presenter;
    private DecisionLogInteractor interactor;
    private User testUser;

    @BeforeEach
    void setUp() {
        userDataAccessObject = new TestDecisionLogUserDataAccess();
        presenter = new TestDecisionLogOutputBoundary();
        interactor = new DecisionLogInteractor(userDataAccessObject, presenter);
        
        // Create test user with various decisions
        ArrayList<Decision> decisions = new ArrayList<>();
        decisions.add(new Decision
                (18, "Test Question 1", "Response 1",
                        1000.0, 5, 0));
        decisions.add(new Decision
                (19, "Test Question 2", "Response 2",
                        -500.0, -3, 0));
        decisions.add(new Decision
                (20, "Test Question 3", "Response 3",
                        0.0, 0, 0));
        
        testUser = new CommonUser("testUser", "password", 20, false, 
            "TestName", new Avatar(), 75, 50000.0, new Assets(), 
            new Liabilities(), decisions);
    }

    @Test
    void switchToHomepageView_WithValidDecisions() {
        userDataAccessObject.setCurrentUser(testUser);
        interactor.switchToHomepageView();

        DecisionLogOutputData outputData = presenter.getOutputData();
        assertNotNull(outputData);
        assertEquals("testUser", outputData.getUsername());
        assertEquals(3, outputData.getDecisions().size());
        assertFalse(outputData.isDarkMode());

        // Verify decision details
        List<Decision> decisions = outputData.getDecisions();
        assertEquals(18, decisions.get(0).getAge());
        assertEquals(1000.0, decisions.get(0).getNetWorthChange());
        assertEquals(5, decisions.get(0).getHappinessChange());
    }

    @Test
    void switchToHomepageView_WithEmptyDecisions() {
        User emptyUser = new CommonUser("emptyUser", "password", 18, false,
            "EmptyName", new Avatar(), 75, 50000.0, new Assets(),
            new Liabilities(), new ArrayList<>());
        userDataAccessObject.setCurrentUser(emptyUser);
        
        interactor.switchToHomepageView();

        DecisionLogOutputData outputData = presenter.getOutputData();
        assertNotNull(outputData);
        assertTrue(outputData.getDecisions().isEmpty());
    }

    @Test
    void switchToHomepageView_WithDarkMode() {
        User darkModeUser = new CommonUser("darkUser", "password", 18, true,
            "DarkName", new Avatar(), 75, 50000.0, new Assets(),
            new Liabilities(), new ArrayList<>());
        userDataAccessObject.setCurrentUser(darkModeUser);
        
        interactor.switchToHomepageView();

        DecisionLogOutputData outputData = presenter.getOutputData();
        assertTrue(outputData.isDarkMode());
    }

    @Test
    void switchToHomepageView_WithNullUser() {
        userDataAccessObject.setCurrentUser(null);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            interactor.switchToHomepageView();
        });
        assertEquals("No user found", exception.getMessage());
    }

    // Test data access object implementation
    private static class TestDecisionLogUserDataAccess implements DecisionLogUserDataAccessInterface {
        private User currentUser;

        public void setCurrentUser(User user) {
            this.currentUser = user;
        }

        @Override
        public User getCurrentUser() {
            if (currentUser == null) {
                throw new RuntimeException("No user found");
            }
            return currentUser;
        }
    }

    // Test presenter implementation
    private static class TestDecisionLogOutputBoundary implements DecisionLogOutputBoundary {
        private DecisionLogOutputData outputData;

        @Override
        public void switchToHomepageView(DecisionLogOutputData outputData) {
            this.outputData = outputData;
        }

        public DecisionLogOutputData getOutputData() {
            return outputData;
        }
    }
}
