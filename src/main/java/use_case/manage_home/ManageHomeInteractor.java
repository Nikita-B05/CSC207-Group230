package use_case.manage_home;

import entity.User;

/**
 * The Manage Home Interactor.
 */
public class ManageHomeInteractor implements ManageHomeInputBoundary{

    private final ManageHomeDataAccessInterface userDataAccessObject;
    private final ManageHomeOutputBoundary manageHomePresenter;

    public ManageHomeInteractor(ManageHomeDataAccessInterface userDataAccessInterface,
                                 ManageHomeOutputBoundary manageHomeOutputBoundary)
    {
        this.userDataAccessObject = userDataAccessInterface;
        this.manageHomePresenter = manageHomeOutputBoundary;
    }

    @Override
    public void execute(ManageHomeInputData manageHomeInputData) {
        User user = userDataAccessObject.getCurrentUser();
        if (manageHomeInputData.isBuying()) {
            // Fail if user has not selected a house to buy
            if (manageHomeInputData.getNewHome() == 0.0) {
                manageHomePresenter.prepareFailView("Please select a house to buy.");
            }
            // Check that user has sufficient cash, and either confirm buy or fail.
            else if (user.getAssets() == null || user.getAssets().getCash() < manageHomeInputData.getNewHome()) {
                manageHomePresenter.prepareFailView("You do not have the cash for this transaction.");
            }
            else {
                userDataAccessObject.updateUserCash(
                        user.getAssets().getCash() - manageHomeInputData.getNewHome());
                userDataAccessObject.updateUserHome(manageHomeInputData.getNewHome());
                user = userDataAccessObject.getCurrentUser();
                manageHomePresenter.prepareBuySuccessView(
                        "Home bought successfully.",
                        manageHomeInputData.getNewHome(),
                        user.getAssets().getCash()
                );
            }
        }
        else {
            // Fail if user does not have a house to sell
            if (user.getAssets().getHome() == 0.0) {
                manageHomePresenter.prepareFailView("You don't own a house to sell.");
            } else {
                userDataAccessObject.updateUserCash(user.getAssets().getCash() + user.getAssets().getHome());
                userDataAccessObject.updateUserHome(0.0);
                user = userDataAccessObject.getCurrentUser();
                manageHomePresenter.prepareSellSuccessView(
                        "Home sold successfully.", user.getAssets().getCash());
            }
        }
    }

    @Override
    public void switchToAssetManagerView() {
        ManageHomeOutputData outputData = new ManageHomeOutputData();
        outputData.setDarkMode(userDataAccessObject.getCurrentUser().isDarkMode());
        manageHomePresenter.switchToAssetManagerView(outputData);
    }
}
