package use_case.choose_asset;

import entity.User;
import use_case.login.ChooseAssetStockDataAccessInterface;

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
                user.getAssets() == null ? 0 : user.getAssets().getHome(),
                stockDataAccessObject.getStockCodes()
        );
        chooseAssetPresenter.switchToManageHomeView(outputData);
    }

    @Override
    public void switchToManageStockView() {
        User user = userDataAccessObject.getCurrentUser();
        ChooseAssetOutputData outputData = new ChooseAssetOutputData(
                user.getUsername(),
                user.isDarkMode(),
                user.getAssets() == null ? 0 : user.getAssets().getHome(),
                stockDataAccessObject.getStockCodes()
        );
        chooseAssetPresenter.switchToManageStockView(outputData);
    }

    @Override
    public void switchToGameDecisionView() {
//        chooseAssetPresenter.switchToGameDecisionView();
    }
}
