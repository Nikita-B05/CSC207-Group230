package use_case.manage_home;

import data_access.MongoDBUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManageHomeInteractorTest {
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
    void buyingWithoutSelectingHome() {
        ManageHomeOutputBoundary notSelectedHomePresenter = new ManageHomeOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                assertTrue(true);
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double newHome, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageHomeOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        ManageHomeInputBoundary interactor = new ManageHomeInteractor(userRepository, notSelectedHomePresenter);
        ManageHomeInputData inputData = new ManageHomeInputData(0, true);
        interactor.execute(inputData);
    }

    @Test
    void insufficientCashToBuy() {
        ManageHomeOutputBoundary insufficientPresenter = new ManageHomeOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                assertTrue(true);
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double newHome, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageHomeOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        ManageHomeInputBoundary interactor = new ManageHomeInteractor(userRepository, insufficientPresenter);
        ManageHomeInputData inputData = new ManageHomeInputData(100, true);
        interactor.execute(inputData);
    }

    @Test
    void noHomeToSell() {
        ManageHomeOutputBoundary noHomePresenter = new ManageHomeOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                assertTrue(true);
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double newHome, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageHomeOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        ManageHomeInputBoundary interactor = new ManageHomeInteractor(userRepository, noHomePresenter);
        ManageHomeInputData inputData = new ManageHomeInputData(100, false);
        interactor.execute(inputData);
    }

    @Test
    void buyHomeSuccessView() {
        ManageHomeOutputBoundary buyHomeSuccessPresenter = new ManageHomeOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                fail("Fail view is not expected");
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double newHome, double cash) {
                assertTrue(true);
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageHomeOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        userRepository.updateUserCash(100);
        ManageHomeInputBoundary interactor = new ManageHomeInteractor(userRepository, buyHomeSuccessPresenter);
        ManageHomeInputData inputData = new ManageHomeInputData(100, true);
        interactor.execute(inputData);
        // Delete for other tests
        userRepository.updateUserHome(0);
    }

    @Test
    void sellHomeSuccessView() {
        ManageHomeOutputBoundary sellHomeSuccessPresenter = new ManageHomeOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                fail("Fail view is not expected");
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double newHome, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                assertTrue(true);
            }

            @Override
            public void switchToAssetManagerView(ManageHomeOutputData outputData) {
                fail("Switching to asset manager is view not expected");
            }
        };

        userRepository.updateUserHome(100);
        ManageHomeInputBoundary interactor = new ManageHomeInteractor(userRepository, sellHomeSuccessPresenter);
        ManageHomeInputData inputData = new ManageHomeInputData(0, false);
        interactor.execute(inputData);
        // Delete for other tests
        userRepository.updateUserCash(0);
    }

    @Test
    void switchToAssetManagerView() {
        ManageHomeOutputBoundary assetManagerPresenter = new ManageHomeOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                fail("Fail view is not expected");
            }

            @Override
            public void prepareBuySuccessView(String successMessage, double newHome, double cash) {
                fail("Buy success view is not expected");
            }

            @Override
            public void prepareSellSuccessView(String successMessage, double cash) {
                fail("Sell success view is not expected");
            }

            @Override
            public void switchToAssetManagerView(ManageHomeOutputData outputData) {
                assertFalse(outputData.isDarkMode());
            }
        };

        ManageHomeInputBoundary interactor = new ManageHomeInteractor(userRepository, assetManagerPresenter);
        interactor.switchToAssetManagerView();
    }
}
