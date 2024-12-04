package use_case.game_over;

import data_access.MongoDBUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOverInteractorTest {

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
    void switchToHomepageViewTest() {
        GameOverOutputBoundary homepagePresenter = new GameOverOutputBoundary() {
            @Override
            public void prepareHomepageView(GameOverOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }
        };

        GameOverInputBoundary interactor = new GameOverInteractor(
                userRepository, homepagePresenter);
        interactor.switchToHomeview(new GameOverInputData(
                new Assets(),
                100,
                new Avatar(),
                false,
                30,
                "king james",
                "testing"
        ));
    }
}
