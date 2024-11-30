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
                user.getAssets() == null ? 0 : user.getAssets().getHome()
        );
        chooseAssetPresenter.switchToManageHomeView(outputData);
    }

    @Override
    public void switchToManageStockView() {
        User user = userDataAccessObject.getCurrentUser();
        // TODO
//        stockDataAccessObject.setDate(getDate(user.getAge()));
        ChooseAssetOutputData outputData = new ChooseAssetOutputData(
                user.getUsername(),
                user.isDarkMode(),
                user.getAssets() == null ? 0.0 : user.getAssets().getCash(),
                stockDataAccessObject.getStockNames(),
                stockDataAccessObject.getNameToCode(),
                stockDataAccessObject.getCodeToPrice()
        );
        chooseAssetPresenter.switchToManageStockView(outputData);
    }

    private String getDate(int age) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Add a certain number of days
        int daysToSubtract = 100 - age;
        LocalDate newDate = currentDate.minusDays(daysToSubtract);

        // Format the date to YYYY-MM-DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = newDate.format(formatter);

        return formattedDate;
    }

    @Override
    public void switchToGameDecisionView() {
        User user = userDataAccessObject.getCurrentUser();
        chooseAssetPresenter.switchToGameDecisionView(new ChooseAssetOutputData(user.getUsername()));

    }
}
