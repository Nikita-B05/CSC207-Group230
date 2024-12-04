package use_case.game_success;

import data_access.MongoDBUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_case.game_over.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameSuccessInteractorTest {

    private static MongoDBUserDataAccessObject userRepository;

    @BeforeAll
    public static void setUp() {
        User user = new CommonUser("testing", "password");
        user.setCharacterName("Larry");
        userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.setCurrentUsername("testing");
        userRepository.save(user);
    }

    @AfterAll
    public static void tearDown() {
        userRepository.deleteUser("testing");
    }

    @Test
    void switchToHomepageViewTest() {
        GameSuccessOutputBoundary homepagePresenter = new GameSuccessOutputBoundary() {
            @Override
            public void prepareHomepageView(GameSuccessOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
                assertEquals(new Avatar().getId(), outputData.getAvatar().getId());
                assertEquals("Larry", outputData.getCharacterName());
                assertFalse(outputData.isDarkMode());
            }
        };

        GameSuccessInputBoundary interactor = new GameSuccessInteractor(homepagePresenter, userRepository);

        // Pass some data and switch to the homepage view
        interactor.switchToHomepageView();

        User user = userRepository.getCurrentUser();
        assertEquals(user.getAssets().getCash(), 0);
        assertEquals(user.getAssets().getHome(), 0);
        assertEquals(user.getAssets().getStocks().size(), 0);
        assertEquals(user.getAssets().getCar(), 0);
    }
}
