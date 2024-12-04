package use_case.decision_log;

import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DecisionLogInteractorTest {

    // Test doubles (fake implementations)
    static class TestUser implements User {
        private final String username;
        private final List<Decision> decisions;
        private final boolean darkMode;

        TestUser(String username, List<Decision> decisions, boolean darkMode) {
            this.username = username;
            this.decisions = decisions;
            this.darkMode = darkMode;
        }

        @Override
        public void setCharacterName(String characterName) {

        }

        @Override
        public void setAvatar(Avatar avatar) {

        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getPassword() {
            return "";
        }

        @Override
        public ArrayList<Decision> getDecisions() {
            return (ArrayList<Decision>) decisions;
        }

        @Override
        public double getNetWorth(Map<String, Double> stockPrices) {
            return 0;
        }

        @Override
        public void setDarkMode(boolean isDarkMode) {

        }

        @Override
        public void buyStock(String stockCode, int quantity, double buyPrice) {

        }

        @Override
        public boolean canBuyStock(String stockCode, int quantity, Map<String, Double> stockPrices) {
            return false;
        }

        @Override
        public double sellStock(String stockCode, int quantity, double sellPrice) {
            return 0;
        }

        @Override
        public boolean isValidSell(String stockCode, int quantity) {
            return false;
        }

        @Override
        public int getAge() {
            return 0;
        }

        @Override
        public void setAge(int age) {

        }

        @Override
        public void changeHappiness(int happiness) {

        }

        @Override
        public void addDecision(Decision decision) {

        }

        @Override
        public void modifySalary(double modification) {

        }

        @Override
        public Map<Integer, Question> getQuestion() {
            return Map.of();
        }

        @Override
        public void setAssets(Assets assets) {

        }

        @Override
        public void setDecisions(ArrayList<Decision> decisions) {

        }

        @Override
        public void setSalary(double salary) {

        }

        @Override
        public void setHappiness(int happiness) {

        }

        @Override
        public boolean isDarkMode() {
            return darkMode;
        }

        @Override
        public String getCharacterName() {
            return "";
        }

        @Override
        public Avatar getAvatar() {
            return null;
        }

        @Override
        public int getHappiness() {
            return 0;
        }

        @Override
        public double getSalary() {
            return 0;
        }

        @Override
        public Assets getAssets() {
            return null;
        }

        @Override
        public Liabilities getLiabilities() {
            return null;
        }

        // Implement other required methods from User interface with dummy returns
        // Add any other required method implementations here...
    }

    static class TestUserDataAccess implements DecisionLogUserDataAccessInterface {
        private final User currentUser;

        TestUserDataAccess(User user) {
            this.currentUser = user;
        }

        @Override
        public User getCurrentUser() {
            return currentUser;
        }
    }

    static class TestDecisionLogPresenter implements DecisionLogOutputBoundary {
        private DecisionLogOutputData lastOutputData;

        @Override
        public void switchToHomepageView(DecisionLogOutputData decisionLogOutputData) {
            this.lastOutputData = decisionLogOutputData;
        }

        public DecisionLogOutputData getLastOutputData() {
            return lastOutputData;
        }
    }

    @Test
    void switchToHomepageView_WithDecisions_Success() {
        // Create test data
        List<Decision> decisions = Arrays.asList(
                new Decision(25, "Test Decision 1", "Response 1", 1000.0, 5, 500.0),
                new Decision(26, "Test Decision 2", "Response 2", -500.0, -2, -200.0)
        );

        TestUser user = new TestUser("testUser", decisions, true);
        TestUserDataAccess userDataAccess = new TestUserDataAccess(user);
        TestDecisionLogPresenter presenter = new TestDecisionLogPresenter();

        DecisionLogInteractor interactor = new DecisionLogInteractor(userDataAccess, presenter);

        // Execute the method
        interactor.switchToHomepageView();

        // Verify the results
        DecisionLogOutputData outputData = presenter.getLastOutputData();
        assertEquals("testUser", outputData.getUsername());
        assertEquals(2, outputData.getDecisions().size());
        assertTrue(outputData.isDarkMode());
        assertEquals(decisions, outputData.getDecisions());
    }

    @Test
    void switchToHomepageView_WithEmptyDecisions_Success() {
        // Create test data with empty decisions list
        List<Decision> emptyDecisions = new ArrayList<>();
        TestUser user = new TestUser("testUser", emptyDecisions, false);
        TestUserDataAccess userDataAccess = new TestUserDataAccess(user);
        TestDecisionLogPresenter presenter = new TestDecisionLogPresenter();

        DecisionLogInteractor interactor = new DecisionLogInteractor(userDataAccess, presenter);

        // Execute the method
        interactor.switchToHomepageView();

        // Verify the results
        DecisionLogOutputData outputData = presenter.getLastOutputData();
        assertEquals("testUser", outputData.getUsername());
        assertTrue(outputData.getDecisions().isEmpty());
        assertFalse(outputData.isDarkMode());
    }

    @Test
    void switchToHomepageView_WithNullDecisions_Success() {
        // Create test data with null decisions list
        TestUser user = new TestUser("testUser", null, false);
        TestUserDataAccess userDataAccess = new TestUserDataAccess(user);
        TestDecisionLogPresenter presenter = new TestDecisionLogPresenter();

        DecisionLogInteractor interactor = new DecisionLogInteractor(userDataAccess, presenter);

        // Execute the method
        interactor.switchToHomepageView();

        // Verify the results
        DecisionLogOutputData outputData = presenter.getLastOutputData();
        assertEquals("testUser", outputData.getUsername());
        assertNull(outputData.getDecisions());
        assertFalse(outputData.isDarkMode());
    }

    @Test
    void testDecisionLogInputData() {
        // Create test data
        String testUsername = "testUser";
        DecisionLogInputData inputData = new DecisionLogInputData(testUsername);
        List<Decision> testDecisions = Arrays.asList(
                new Decision(25, "Test Decision 1", "Response 1", 1000.0, 5, 500.0),
                new Decision(26, "Test Decision 2", "Response 2", -500.0, -2, -200.0)
        );

        // Test initial state
        assertEquals(testUsername, inputData.getUsername());
        assertTrue(inputData.getDecisions().isEmpty());

        // Test setting decisions
        inputData.setDecisions(testDecisions);
        assertTrue(inputData.getDecisions().isEmpty()); // Current implementation always returns empty list

        // Test with null decisions
        inputData.setDecisions(null);
        assertTrue(inputData.getDecisions().isEmpty());

        // Test with empty list
        inputData.setDecisions(new ArrayList<>());
        assertTrue(inputData.getDecisions().isEmpty());
    }

    @Test
    void testDecisionLogOutputData_AllFields() {
        // Create test data
        String testUsername = "testUser";
        List<Decision> testDecisions = Arrays.asList(
                new Decision(25, "Test Decision 1", "Response 1", 1000.0, 5, 500.0),
                new Decision(26, "Test Decision 2", "Response 2", -500.0, -2, -200.0)
        );
        boolean isDarkMode = true;

        // Create output data
        DecisionLogOutputData outputData = new DecisionLogOutputData(testUsername, testDecisions, isDarkMode);

        // Test all getters
        assertEquals(testUsername, outputData.getUsername());
        assertEquals(testDecisions, outputData.getDecisions());
        assertTrue(outputData.isDarkMode());
        assertNull(outputData.getHome()); // Tests default null return
        assertNull(outputData.getAvatar()); // Tests default null return
        assertNull(outputData.getName()); // Tests default null return
        assertEquals(0, outputData.getAge()); // Tests default age value
    }

    @Test
    void testDecisionLogOutputData_NullValues() {
        // Create output data with null values
        DecisionLogOutputData outputData = new DecisionLogOutputData(null, null, false);

        // Test null handling
        assertNull(outputData.getUsername());
        assertNull(outputData.getDecisions());
        assertFalse(outputData.isDarkMode());
    }

    @Test
    void testDecisionLogOutputData_EmptyValues() {
        // Create output data with empty values
        DecisionLogOutputData outputData = new DecisionLogOutputData("", new ArrayList<>(), false);

        // Test empty values
        assertEquals("", outputData.getUsername());
        assertTrue(outputData.getDecisions().isEmpty());
        assertFalse(outputData.isDarkMode());
    }
}
