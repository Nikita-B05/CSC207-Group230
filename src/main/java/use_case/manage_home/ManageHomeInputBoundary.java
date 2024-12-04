package use_case.manage_home;

/**
 * Input Boundary for actions which are related to managing home.
 */
public interface ManageHomeInputBoundary {

    /**
     * Executes the Manage Home use case.
     * @param manageHomeInputData the input data
     */
    void execute(ManageHomeInputData manageHomeInputData);

    /**
     * Switches to Asset Manager view.
     */
    void switchToAssetManagerView();
}
