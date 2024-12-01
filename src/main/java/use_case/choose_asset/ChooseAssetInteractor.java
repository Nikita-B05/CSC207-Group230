package use_case.choose_asset;

import entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChooseAssetInteractor implements ChooseAssetInputBoundary {
    private final ChooseAssetDataAccessInterface userDataAccessObject;
    private final ChooseAssetOutputBoundary chooseAssetPresenter;
    private final ChooseAssetStockDataAccessInterface stockDataAccessObject;

    public ChooseAssetInteractor(ChooseAssetDataAccessInterface userDataAccessInterface,
                                 ChooseAssetOutputBoundary chooseAssetOutputBoundary,
                                 ChooseAssetStockDataAccessInterface stockDataAccessInterface)
    {
        this.userDataAccessObject = userDataAccessInterface;
        this.chooseAssetPresenter = chooseAssetOutputBoundary;
        this.stockDataAccessObject = stockDataAccessInterface;
    }

    @Override
    public void switchToManageHomeView() {
        User user = userDataAccessObject.getCurrentUser();
        ChooseAssetOutputData outputData = new ChooseAssetOutputData(
                user.getUsername(),
                user.isDarkMode(),
                user.getAssets().getCash(),
                user.getAssets().getHome()
        );
        chooseAssetPresenter.switchToManageHomeView(outputData);
    }

    @Override
    public void switchToManageStockView() {
        User user = userDataAccessObject.getCurrentUser();
        // TODO
        stockDataAccessObject.setDate(10);
        ChooseAssetOutputData outputData = new ChooseAssetOutputData(
                user.getUsername(),
                user.isDarkMode(),
                user.getAssets().getCash(),
                stockDataAccessObject.getStockNames(),
                stockDataAccessObject.getNameToCode(),
                stockDataAccessObject.getCodeToPrice()
        );
        chooseAssetPresenter.switchToManageStockView(outputData);
    }

    @Override
    public void switchToGameDecisionView() {
        User user = userDataAccessObject.getCurrentUser();
        chooseAssetPresenter.switchToGameDecisionView(new ChooseAssetOutputData(user.getUsername()));

    }
}
