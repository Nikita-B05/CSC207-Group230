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
        getDate(10);
//        stockDataAccessObject.setDate(getDate(user.getAge()));
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

    private String getDate(int age) {
        // Add a certain number of days
        int year = 2000 + (age - 18);
        String date = year + "-01-31";
        return date;
    }

    @Override
    public void switchToGameDecisionView() {
        User user = userDataAccessObject.getCurrentUser();
        chooseAssetPresenter.switchToGameDecisionView(new ChooseAssetOutputData(user.getUsername()));

    }
}
