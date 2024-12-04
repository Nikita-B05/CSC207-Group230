package interface_adapter.manage_home;

import use_case.manage_home.ManageHomeInputBoundary;
import use_case.manage_home.ManageHomeInputData;

/**
 * The controller for the Manage Home Use Case.
 */
public class ManageHomeController {

    private final ManageHomeInputBoundary manageHomeInteractor;

    public ManageHomeController(ManageHomeInputBoundary manageHomeInteractor) {
        this.manageHomeInteractor = manageHomeInteractor;
    }

    /**
     * Executes the Manage Home Use Case.
     * @param newHome the value of the new home.
     * @param isBuying is the user buying or selling.
     */
    public void execute(double newHome, boolean isBuying) {
        final ManageHomeInputData manageHomeInputData = new ManageHomeInputData(newHome, isBuying);
        manageHomeInteractor.execute(manageHomeInputData);
    }

    /**
     * Switches to Asset Manager View.
     */
    public void switchToAssetManagerView() {
        manageHomeInteractor.switchToAssetManagerView();
    }
}
