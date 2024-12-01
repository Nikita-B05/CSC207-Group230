package use_case.choose_asset;

import data_access.MongoDBUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stock_api.PolygonStockDataAccessObject;
import stock_api.VantageStockDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;;

public class ChooseAssetInteractorTest {
    private static VantageStockDataAccessObject stockClient;
    private static MongoDBUserDataAccessObject userRepository;

    @BeforeAll
    public static void setUp() {
        stockClient = new VantageStockDataAccessObject();
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
    void switchToManageHomeView() {
        ChooseAssetOutputBoundary manageHomePresenter = new ChooseAssetOutputBoundary() {
            @Override
            public void switchToManageHomeView(ChooseAssetOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }

            @Override
            public void switchToManageStockView(ChooseAssetOutputData outputData) {
                fail("Use case Manage Stock View is unexpected.");
            }

            @Override
            public void switchToGameDecisionView(ChooseAssetOutputData outputData) {
                fail("Use case Game Decision View is unexpected.");
            }
        };

        ChooseAssetInputBoundary interactor = new ChooseAssetInteractor(
                userRepository, manageHomePresenter, stockClient);
        interactor.switchToManageHomeView();
    }

    @Test
    void switchToManageStockView() {
        ChooseAssetOutputBoundary manageStockPresenter = new ChooseAssetOutputBoundary() {
            @Override
            public void switchToManageHomeView(ChooseAssetOutputData outputData) {
                fail("Use case Manage Home View is unexpected.");
            }

            @Override
            public void switchToManageStockView(ChooseAssetOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }

            @Override
            public void switchToGameDecisionView(ChooseAssetOutputData outputData) {
                fail("Use case Game Decision View is unexpected.");
            }
        };

        ChooseAssetInputBoundary interactor = new ChooseAssetInteractor(
                userRepository, manageStockPresenter, stockClient);
        interactor.switchToManageStockView();
    }

    @Test
    void switchToGameDecisionView() {
        ChooseAssetOutputBoundary gameDecisionPresenter = new ChooseAssetOutputBoundary() {
            @Override
            public void switchToManageHomeView(ChooseAssetOutputData outputData) {
                fail("Use case Manage Home View is unexpected.");
            }

            @Override
            public void switchToManageStockView(ChooseAssetOutputData outputData) {
                fail("Use case Manage Stock View is unexpected.");
            }

            @Override
            public void switchToGameDecisionView(ChooseAssetOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }
        };

        ChooseAssetInputBoundary interactor = new ChooseAssetInteractor(
                userRepository, gameDecisionPresenter, stockClient);
        interactor.switchToGameDecisionView();
    }
}
