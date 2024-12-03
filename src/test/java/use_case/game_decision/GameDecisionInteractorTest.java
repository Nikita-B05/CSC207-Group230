package use_case.game_decision;

import data_access.MongoDBUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stock_api.PolygonStockDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class GameDecisionInteractorTest {

    private static MongoDBUserDataAccessObject userRepository;
    private static PolygonStockDataAccessObject stockRepository;


    @BeforeAll
    public static void setUp() {
        User user = new CommonUser("testing", "password");
        userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.setCurrentUsername("testing");
        userRepository.save(user);
        stockRepository = new PolygonStockDataAccessObject();
    }

    @AfterAll
    public static void tearDown() {
        userRepository.deleteUser("testing");
    }

    @Test
    void switchToAssetsManagerTest() {
        userRepository.setCurrentUsername("testing");

        GameDecisionOutputBoundary assetManagerPresenter = new GameDecisionOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareGameSuccessView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareAssetsView(GameDecisionOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }

            @Override
            public void prepareGameOverView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareHomepageView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }
        };

        GameDecisionInputBoundary interactor = new GameDecisionInteractor(userRepository, assetManagerPresenter, stockRepository);
        interactor.switchToAssetsManager(new GameDecisionInputData(
                "testing",
                18,
                "King James",
                false,
                new Question(18, "Who is the best basketball playa?"),
                new Assets(),
                new Avatar(),
                new Decision(18, "Who is the best Basketball Player?", "King James", 10.00, 10, 14.432),
                30,
                10
        ));

    }

    @Test
    void switchToGameOverTest(){
        userRepository.setCurrentUsername("testing");

        GameDecisionOutputBoundary gameOverPresenter = new GameDecisionOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareAssetsView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareGameSuccessView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
                }

            @Override
            public void prepareGameOverView(GameDecisionOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }

            @Override
            public void prepareHomepageView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }
        };

        GameDecisionInputBoundary interactor = new GameDecisionInteractor(userRepository, gameOverPresenter, stockRepository);
        interactor.switchToGameOver(new GameDecisionInputData(
                "testing",
                18,
                "King James",
                false,
                new Question(18, "Who is the best basketball playa?"),
                new Assets(),
                new Avatar(),
                new Decision(18, "Who is the best Basketball Player?", "King James", 10.00, 10, 14.432),
                30,
                10
        ));
    }

    @Test
    void switchToHomeviewTest(){
        userRepository.setCurrentUsername("testing");

        GameDecisionOutputBoundary homepagePresenter = new GameDecisionOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareAssetsView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareGameOverView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareGameSuccessView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareHomepageView(GameDecisionOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }
        };

        GameDecisionInputBoundary interactor = new GameDecisionInteractor(userRepository, homepagePresenter, stockRepository);
        interactor.switchToHomeview(new GameDecisionInputData(
                "testing",
                18,
                "King James",
                false,
                new Question(18, "Who is the best basketball playa?"),
                new Assets(),
                new Avatar(),
                new Decision(18, "Who is the best Basketball Player?", "King James", 10.00, 10, 14.432),
                30,
                10
                ));

    }

    @Test
    void switchToGameSuccess(){
        userRepository.setCurrentUsername("testing");

        GameDecisionOutputBoundary gameSuccessPresenter = new GameDecisionOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareAssetsView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareGameOverView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareGameSuccessView(GameDecisionOutputData outputData) {
                assertEquals("testing", outputData.getUsername());            }

            @Override
            public void prepareHomepageView(GameDecisionOutputData outputData) {
                fail("Use case is unexpected");
            }
        };

        GameDecisionInputBoundary interactor = new GameDecisionInteractor(userRepository, gameSuccessPresenter, stockRepository);
        interactor.switchToGameSuccess(new GameDecisionInputData(
                "testing",
                18,
                "King James",
                false,
                new Question(18, "Who is the best basketball playa?"),
                new Assets(),
                new Avatar(),
                new Decision(18, "Who is the best Basketball Player?", "King James", 10.00, 10, 14.432),
                30,
                10
        ));
    }
}
