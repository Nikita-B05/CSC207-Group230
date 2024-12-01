package use_case.manage_stock;

import data_access.MongoDBUserDataAccessObject;
import entity.Assets;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stock_api.PolygonStockDataAccessObject;
import stock_api.VantageStockDataAccessObject;

import static org.junit.jupiter.api.Assertions.*;

public class ManageStockInteractorTest {
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
    void invalidQuantity() {
        ManageStockOutputBoundary invalidQuantityPresenter = new ManageStockOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                assertTrue(true);
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageStockOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        ManageStockInputBoundary interactor = new ManageStockInteractor(
                userRepository, stockClient, invalidQuantityPresenter);
        // quantity is not an int
        ManageStockInputData inputData = new ManageStockInputData("example", "invalid", true);
        interactor.execute(inputData);
        // quantity is too small
        inputData = new ManageStockInputData("example", "-1", true);
        interactor.execute(inputData);
        // quantity is too big
        inputData = new ManageStockInputData("example", "1001", true);
        interactor.execute(inputData);
    }

    @Test
    void buyFailView() {
        ManageStockOutputBoundary buyFailPresenter = new ManageStockOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                assertTrue(true);
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageStockOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        ManageStockInputBoundary interactor = new ManageStockInteractor(
                userRepository, stockClient, buyFailPresenter);
        ManageStockInputData inputData = new ManageStockInputData("AAPL", "10", true);
        interactor.execute(inputData);
    }

    @Test
    void sellFailView() {
        ManageStockOutputBoundary sellFailPresenter = new ManageStockOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                assertTrue(true);
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageStockOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        ManageStockInputBoundary interactor = new ManageStockInteractor(
                userRepository, stockClient, sellFailPresenter);
        ManageStockInputData inputData = new ManageStockInputData("AAPL", "10", false);
        interactor.execute(inputData);
    }

    @Test
    void buySuccessView() {
        ManageStockOutputBoundary buySuccessPresenter = new ManageStockOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                fail("Fail view is not expected");
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double cash) {
                assertTrue(true);
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageStockOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        userRepository.updateUserCash(100_000);
        ManageStockInputBoundary interactor = new ManageStockInteractor(
                userRepository, stockClient, buySuccessPresenter);
        ManageStockInputData inputData = new ManageStockInputData("AAPL", "10", true);
        interactor.execute(inputData);
        userRepository.updateAssets(new Assets());
    }

    @Test
    void sellSuccessView() {
        ManageStockOutputBoundary buySuccessPresenter = new ManageStockOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                fail("Fail view is not expected");
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double cash) {
                assertTrue(true);
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                assertTrue(true);
            }

            @Override
            public void switchToAssetManagerView(ManageStockOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        userRepository.updateUserCash(100_000);
        ManageStockInputBoundary interactor = new ManageStockInteractor(
                userRepository, stockClient, buySuccessPresenter);
        ManageStockInputData inputData = new ManageStockInputData("AAPL", "10", true);
        interactor.execute(inputData);
        inputData = new ManageStockInputData("AAPL", "10", false);
        interactor.execute(inputData);
        userRepository.updateAssets(new Assets());
    }

    @Test
    void switchToAssetManagerView() {
        ManageStockOutputBoundary assetManagerPresenter = new ManageStockOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                fail("Fail view is not expected");
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageStockOutputData outputData) {
                assertFalse(outputData.isDarkMode());
            }
        };

        ManageStockInputBoundary interactor = new ManageStockInteractor(
                userRepository, stockClient, assetManagerPresenter);
        interactor.switchToAssetManagerView();
    }
}
