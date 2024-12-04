package use_case.decision_log;

import entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.time.LocalDateTime;
import data_access.MongoDBUserDataAccessObject;
import stock_api.PolygonStockDataAccessObject;
import use_case.choose_asset.ChooseAssetInputBoundary;
import use_case.choose_asset.ChooseAssetInteractor;
import use_case.choose_asset.ChooseAssetOutputBoundary;
import use_case.choose_asset.ChooseAssetOutputData;
import use_case.homepage.HomepageOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class DecisionLogInteractorTest {

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
    void switchToHomepageView() {
        DecisionLogOutputBoundary decisionLogPresenter = new DecisionLogOutputBoundary() {
            @Override
            public void switchToHomepageView(DecisionLogOutputData decisionLogoutputData) {
                assertEquals("testing", decisionLogoutputData.getUsername());
            }
        };

        DecisionLogInputBoundary interactor = new DecisionLogInteractor(
                userRepository, decisionLogPresenter);
        interactor.switchToHomepageView();
    }
}
