package use_case.game_over;

import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.game_decision.GameDecisionInputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameOverInteractorTest {
    private TestGameOverUserDataAccess userDataAccessObject;
    private TestGameOverOutputBoundary presenter;
    private GameOverInteractor interactor;
    private User testUser;

    @BeforeEach
    void setUp() {
        userDataAccessObject = new TestGameOverUserDataAccess();
        presenter = new TestGameOverOutputBoundary();
        interactor = new GameOverInteractor(userDataAccessObject, presenter);

        // Create test user with game state
        ArrayList<Decision> decisions = new ArrayList<>();
        decisions.add(new Decision(
                22, "Invest in a sketchy crypto or save the money?",
                "Invest life savings in crypto", -50000.0,
                -5, 0));

        testUser = new CommonUser(
            "failedUser",
            "password",
            22,           // Age at game over
            false,       // Dark mode
            "Unlucky Player",
            new Avatar(),
            -10,         // Negative happiness
            30000.0,     // Salary
            new Assets(),
            new Liabilities(),
            decisions
        );
    }

    @Test
    void switchToHomepage_ResetsGameState() {
        Assets assets = null;
        int happiness = 0;
        Avatar avatar = null;
        boolean darkMode = false;
        int age = 0;
        String characterName = null;
        String username = null;
        final GameOverInputData inputData =
                new GameOverInputData(
                        assets, happiness, avatar, darkMode, age, characterName, username);
        userDataAccessObject.setCurrentUser(testUser);
        interactor.switchToHomeview(inputData);

        // Verify user state was reset through the presenter
        GameOverOutputData outputData = presenter.getOutputData();
        assertNotNull(outputData);

        // Verify user data is preserved
        assertEquals("failedUser", outputData.getUsername());
        assertEquals("Unlucky Player", outputData.getCharacterName());
        assertFalse(outputData.isDarkMode());

        // Verify game state was reset
        User resetUser = userDataAccessObject.getCurrentUser();
        assertEquals(18, resetUser.getAge()); // Back to starting age
        assertEquals(75, resetUser.getHappiness()); // Reset happiness
        assertEquals(50000.0, resetUser.getSalary()); // Reset salary
        assertTrue(resetUser.getDecisions().isEmpty()); // Cleared decisions
        assertEquals(0, resetUser.getAssets().getTotal(null)); // Reset assets
        assertEquals(0, resetUser.getLiabilities().getTotal()); // Reset liabilities
    }

    @Test
    void switchToHomepage_WithNullUser() {
        userDataAccessObject.setCurrentUser(null);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            interactor.switchToHomeview(inputData);
        });
        assertEquals("No user found", exception.getMessage());
    }

    @Test
    void switchToHomepage_PreservesUserPreferences() {

        User darkModeUser = new CommonUser(
            "darkUser",
            "password",
            22,
            true,        // Dark mode enabled
            "Dark Player",
            new Avatar(),
            -5,          // Game over state
            0.0,         // Game over state
            new Assets(),
            new Liabilities(),
            new ArrayList<>()
        );

        userDataAccessObject.setCurrentUser(darkModeUser);
        interactor.switchToHomeview(inputData);

        GameOverOutputData outputData = presenter.getOutputData();
        assertTrue(outputData.isDarkMode()); // Dark mode preserved
    }

    @Test
    void switchToHomepage_WithMaximumValues() {
        Assets maxAssets = new Assets();
        maxAssets.changeCash(Double.MAX_VALUE);

        User maxValuesUser = new CommonUser(
            "maxUser",
            "password",
            Integer.MAX_VALUE,
            false,
            "Max Player",
            new Avatar(),
            Integer.MIN_VALUE, // Extreme negative happiness
            Double.MAX_VALUE,  // Extreme salary
            maxAssets,
            new Liabilities(),
            new ArrayList<>()
        );

        userDataAccessObject.setCurrentUser(maxValuesUser);
        interactor.switchToHomeview();

        User resetUser = userDataAccessObject.getCurrentUser();
        assertEquals(18, resetUser.getAge()); // Verify reset from max value
        assertEquals(75, resetUser.getHappiness()); // Verify reset from min value
        assertEquals(50000.0, resetUser.getSalary()); // Verify reset from max value
    }

    // Test data access object implementation
    private static class TestGameOverUserDataAccess implements GameOverUserDataAccessInterface {
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

        @Override
        public void resetGameState(User user) {
            // Reset to initial game state
            user.setAge(18);
            user.setHappiness(75);
            user.setSalary(50000.0);
            user.getDecisions().clear();
            user.setAssets(new Assets());
            user.setLiabilities(new Liabilities());
        }
    }

    // Test presenter implementation
    private static class TestGameOverOutputBoundary implements GameOverOutputBoundary {
        private GameOverOutputData outputData;
        private String errorMessage;

        @Override
        public void prepareFailView(String error) {
            this.errorMessage = error;
        }

        @Override
        public void prepareHomepageView(GameOverOutputData outputData) {
            this.outputData = outputData;
        }

        public GameOverOutputData getOutputData() {
            return outputData;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}